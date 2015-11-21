package NavCam.testmainNavCam;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import NavCam.testmainNavCam.NavCam_TestMain;
import generic.RoverClientRunnable;

public class NavCamClient extends  RoverClientRunnable  {
	
	private Socket socket;
	private static DataOutputStream dout=null;
	private DataInputStream din=null;
	
	private ArrayList<String> command= new ArrayList<String>();
	
	public NavCamClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}
	
	public void run()
	{
			 File file = new File("src/NavCam/testmainNavCam/Commands.txt");                
			 String path = file.getAbsolutePath();
			 
			 try(BufferedReader br = new BufferedReader(new FileReader(path))) {
				    
				    String line = br.readLine();	    	
					 
				    while (line != null) {			        
				        
				        System.out.println("Command:"+line);
				        SendToServer(line);
				        line = br.readLine();
				    }
				    closeAll();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 try {
		            closeAll();
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		}
	
	
	public static void SendToServer(String message)
	{
		try {
            ObjectOutputStream outputToAnotherObject = null;
            ObjectInputStream inputFromAnotherObject = null;
            Thread.sleep(500);
            
            outputToAnotherObject = new ObjectOutputStream(getRoverSocket()
                                                           .getNewSocket().getOutputStream());
			Thread.sleep(1000);

            System.out
            .println("------------");
            System.out
            .println("NavCam Client: Sending request to Socket Server");
            System.out
            .println("------------");
			Thread.sleep(1000);

            outputToAnotherObject.writeObject(message);
            
            // read the server response message
            inputFromAnotherObject = new ObjectInputStream(getRoverSocket()
                                                           .getSocket().getInputStream());
            String message1 = (String) inputFromAnotherObject.readObject();
			Thread.sleep(1000);

            System.out.println("NavCam Client received: "
                               + message1.toUpperCase());
			Thread.sleep(1000);

            // close resources
            inputFromAnotherObject.close();
            outputToAnotherObject.close();
            Thread.sleep(100);
            closeAll();

            
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception error) {
         
        }
	}
	
	

		

}
