package NavCam.testmainNavCam;



import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import generic.RoverServerRunnable;
import NavCam.moduleCombineImage.combine;
import NavCam.moduleConvertTogray.grayscale;
import NavCam.moduleImageToJson.ImageToJSON;
import NavCam.moduleNavCamCapture.NavCamCapture;



public class NavCamServer extends RoverServerRunnable {
	private static DataOutputStream dout=null;
	private static DataInputStream din=null;
	private static Object lock = new Object();
	private static JFrame frame= new JFrame();
	private int n =1;


	public NavCamServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		try {
			while (true) {
				Thread.sleep(1000);

             	System.out.println("NavCam Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				Thread.sleep(1000);

				System.out.println("NavCam Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(
						getRoverServerSocket().getSocket().getOutputStream());
				
				
				
				if(message.equals("NCAM_TURN_ON")){
					Thread.sleep(1000);

					System.out.println("NavCam powered on");
			}
				else if(message.equals("NCAM_CLICK")){
					Thread.sleep(1000);

					System.out.println("NavCam Click");
					frame= new JFrame();
					JPanel panel = new JPanel();
					panel.setLayout(new GridLayout(2,2));
					
					JLabel LeftTop = new JLabel();
					LeftTop.setIcon(new ImageIcon(new ImageIcon("src/NavCam/modulecombineimage/resources/originalimages/lefttop.jpg").getImage().getScaledInstance(300, 220, Image.SCALE_DEFAULT)));

					JLabel RightTop = new JLabel();
					RightTop.setIcon(new ImageIcon(new ImageIcon("src/NavCam/modulecombineimage/resources/originalimages/RightTop.jpg").getImage().getScaledInstance(300, 220, Image.SCALE_DEFAULT)));

					JLabel LeftDown = new JLabel();
					LeftDown.setIcon(new ImageIcon(new ImageIcon("src/NavCam/modulecombineimage/resources/originalimages/LeftDown.jpg").getImage().getScaledInstance(300, 220, Image.SCALE_DEFAULT)));

					JLabel RightDown = new JLabel();
					RightDown.setIcon(new ImageIcon(new ImageIcon("src/NavCam/modulecombineimage/resources/originalimages/RightDown.jpg").getImage().getScaledInstance(300, 220, Image.SCALE_DEFAULT)));

					panel.add(LeftTop);
					panel.add(RightTop);
					panel.add(LeftDown);
					panel.add(RightDown);
						
					frame.add(panel);
					frame.setSize(680, 600);
				    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					frame.setVisible(true);

					waitForFrameToBeClosed();
					    				    
					Thread.sleep(1000);
					combine c = new combine();
					c.run();
					Thread.sleep(1000);
					
					frame.remove(panel);
					JPanel panel2 = new JPanel();

					JLabel combinedImage = new JLabel();
					combinedImage.setIcon(new ImageIcon(new ImageIcon("src/NavCam/modulecombineimage/resources/mixedimages/mixed" +n+".jpg").getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT)));

					panel2.add(combinedImage);
					frame.add(panel2);
					
					frame.setVisible(true);

					waitForFrameToBeClosed();
					    				    
					Thread.sleep(1000);

					grayscale obj = new grayscale();
					
					
					frame.remove(panel2);
					JPanel panel3 = new JPanel();

					JLabel grayImage = new JLabel();
					grayImage.setIcon(new ImageIcon(new ImageIcon("src/NavCam/moduleconverttogray/resources/grayimages/graymixed" +n+".jpg").getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT)));

					panel3.add(grayImage);
					frame.add(panel3);
					
					frame.setVisible(true);

					waitForFrameToBeClosed();
					    				    
					Thread.sleep(1000);
					
					ImageToJSON obj2 = new ImageToJSON();

					Thread.sleep(1000);


			}
				else if(message.equals("NCAM_TURN_OFF")){
					Thread.sleep(1000);

					System.out.println("NavCam powered Off");
			}
				else if(message.equals("NCAM_CAPTURE")){
					Thread.sleep(1000);
					System.out.println("NavCam Capture");

					NavCamCapture nav = new NavCamCapture();
					
					frame= new JFrame();
					JPanel panel = new JPanel();
					
					JLabel image = new JLabel();
					image.setIcon(new ImageIcon(new ImageIcon("src/NavCam/moduleNavCamCapture/resources/capturedImages/"+nav.getSelectedImage()+".jpg").getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT)));

					panel.add(image);
					
					frame.add(panel);
					frame.setSize(680, 600);
				    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					frame.setVisible(true);
					waitForFrameToBeClosed();				    				    
					Thread.sleep(1000);

			}
				else if(message.equalsIgnoreCase("EXIT")){
					Thread.sleep(1000);

					Runtime.getRuntime().exit(0);
				}
				else{
					Thread.sleep(1000);

					System.out.println("Invalid Command");
				}
				
				outputToAnotherObject.writeObject("NavCam Server response - "
						+ message);

				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				/*if (message.equalsIgnoreCase("exit"))
					break;*/
			}
		} catch (IOException ie) {
			System.out.println(ie);
		}
		catch (ClassNotFoundException ie) {
			System.out.println(ie);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void waitForFrameToBeClosed() throws InterruptedException
	{
		 Thread t = new Thread() {
		        public void run() {
		            synchronized(lock) {
		                while (frame.isVisible())
		                    try {
		                        lock.wait();
		                    } catch (InterruptedException e) {
		                        e.printStackTrace();
		                    }
		                System.out.println("Working now");
		            }
		        }
		    };
		    t.start();

		    frame.addWindowListener(new WindowAdapter() {

		        @Override
		        public void windowClosing(WindowEvent arg0) {
		            synchronized (lock) {
		                frame.setVisible(false);
		                lock.notify();
		            }
		        }

		    });

		    t.join();
	}


}