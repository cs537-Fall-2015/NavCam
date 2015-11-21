package NavCam.junk.moduleclientserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import NavCam.moduleCombineImage.combine;


public class Client {

    private BufferedReader in;
    private PrintWriter out;
    private JFrame frame = new JFrame("Capitalize Client");
    private JTextField dataField = new JTextField(80);
    private JTextArea messageArea = new JTextArea(30, 100);

    
    public Client() {

        // Layout GUI
        messageArea.setEditable(false);
        frame.getContentPane().add(dataField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");

        // Add Listeners
        
        dataField.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	
                out.println(dataField.getText());
                   String response;
                try {
                	
                    response = in.readLine();
                	
                    if (response == null || response.equals("")) {
                          System.exit(0);
                      }
                	
                	
                } catch (IOException ex) {
                       response = "Error: " + ex;
                }
                
                messageArea.append(response + "\n");
                dataField.selectAll();
                
            }
        });
    }

   
    public void connectToServer() throws IOException {

        // Get the server address from a dialog box.
        String serverAddress = JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Capitalization Program",
            JOptionPane.QUESTION_MESSAGE);

        // Make connection and initialize streams
        Socket socket = new Socket(serverAddress, 9898);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Consume the initial welcoming messages from the server
        for (int i = 0; i < 9; i++) {
            messageArea.append(in.readLine() + "\n");
        }
    }

  
    public static void main(String[] args) throws Exception {
    	System.out.println("please choose what you want:");
    	System.out.println("1.talk to the server");
    	System.out.println("2.convert json file from server to jpg");
    	Scanner scanner = new Scanner(System.in);
    	String mychoice = scanner.nextLine();
    	if (mychoice.equalsIgnoreCase("1"))
    	{
    		Client client = new Client();
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.frame.pack();
            client.frame.setVisible(true);
            client.connectToServer();
    	}
    	else 
    	{
    		Random rand= new Random();
    		int n = (rand.nextInt((100 - 1) + 1) + 1);
    	
    		String myFilePath = "src/moduleclientserver/resources/jsonfiles/graymixed1.json";
    		
    		// JSONParser is used to parse the data
    		JSONParser parser = new JSONParser();
    		
    		try {
    			String image = (String) parser.parse(new FileReader(myFilePath));
    		
    			byte[] imageInByte = DatatypeConverter.parseBase64Binary(image);
    		
    			FileOutputStream fileOuputStream = new FileOutputStream(
    				    "src/moduleclientserver/resources/clientimages/clientimage.jpg");
    			    fileOuputStream.write(imageInByte);	    
    			    System.out.println("Conversion completed");
    		} catch (IOException | ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
        
    }
}