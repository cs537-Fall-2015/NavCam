package NavCam.testmainNavCam;

import java.io.IOException;
import java.util.ArrayList;
import NavCam.testmainNavCam.NavCamClient;
import NavCam.testmainNavCam.NavCamServer;
import NavCam.testmainNavCam.Ports;
import generic.RoverThreadHandler;


public class NavCam_TestMain {

private ArrayList<String> command= new ArrayList<String>();
	
public static void main(String[] args) {
	try {
		// create a thread for module one
		NavCamServer server = new NavCamServer((int) Ports.NAVCAM_PORT);
		Thread serverThread = RoverThreadHandler.getRoverThreadHandler()
				.getNewThread(server);
		serverThread.start();

		NavCamClient client = new NavCamClient((int) Ports.NAVCAM_PORT, null);
		Thread clientThread = RoverThreadHandler.getRoverThreadHandler()
				.getNewThread(client);
		clientThread.start();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	
	
	

}
