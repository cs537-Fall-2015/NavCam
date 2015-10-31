package module7;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;
public class ModuleSevenClient1 extends RoverClientRunnable{
	public ModuleSevenClient1(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    Thread.sleep(1000);		    
		    
		    outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    System.out.println("Sending request to Server");
		  
		    outputToAnotherObject.writeObject("NCAM_TURN_ON");
		  
		    outputToAnotherObject.writeObject("NCAM_CAPTURE");
		    Thread.sleep(5000);
		  
		    outputToAnotherObject.writeObject("NCAM_CLICK");
		    Thread.sleep(5000);
		    
		    outputToAnotherObject.writeObject("NCAM_TURN_OFF");
		    Thread.sleep(5000);		    
		
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			//System.out.println("Client: Error:" + error.getMessage());
		}
	}
}
