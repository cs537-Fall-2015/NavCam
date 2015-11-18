package NavCam;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import generic.RoverServerRunnable;



public class NavCamServer extends RoverServerRunnable {
	private static DataOutputStream dout=null;
	private static DataInputStream din=null;
	
	public NavCamServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		try {
			while (true) {
             	System.out.println("NavCam Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("MastCam Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(
						getRoverServerSocket().getSocket().getOutputStream());
				
				
				
				if(message.equals("NCAM_TURN_ON")){
					System.out.println("NavCam powered on");
			}
				else if(message.equals("NCAM_CLICK")){
					System.out.println("NavCam Click");
			}
				else if(message.equals("NCAM_TURN_OFF")){
					System.out.println("NavCam powered Off");
			}
				else if(message.equals("NCAM_CAPTURE")){
					System.out.println("NavCam Capture");
			}
				else if(message.equalsIgnoreCase("EXIT")){
					break;
				}
				else{
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
			closeAll();
		} catch (IOException ie) {
			System.out.println(ie);
		}
		catch (ClassNotFoundException ie) {
			System.out.println(ie);
		}
		
		
	}
	
	/*private ServerSocket ss;
	
	public NavCamServer(int port) throws IOException {
		listen(port);
	}
	private void listen(int port) throws IOException {
		
		
		InetAddress add = InetAddress.getLocalHost();

		ss = new ServerSocket(port,0,add);
		

	
		System.out.println("The Server is Running on " + ss.getInetAddress().getHostAddress()+"/"+ss.getLocalPort());
		while (true) {
			Socket s = ss.accept();
			new NavCamServerThread(this, s);
		}
	}
*/

}