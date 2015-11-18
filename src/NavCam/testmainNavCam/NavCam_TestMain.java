package NavCam.testmainNavCam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;

import NavCam.NavCamClient;
import NavCam.NavCamServer;
import NavCam.Ports;
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
	
/*	public static void main(String[] args) throws IOException, InterruptedException {

		Random rand = new Random();
	    int randomNum = rand.nextInt((65535 - 1) + 1) + 1;

	
		new Thread(new Runnable() {@Override
			public void run() {
				try {
					
					server = new NavCamServer(randomNum);					

				} catch (IOException e) {}
			}
		}).start();
		
		
		Thread.sleep(1000);
		
		System.out.println("Waiting for incomming connection ...");
		
		Thread.sleep(3000);
		
		client = new NavCamClient(InetAddress.getLocalHost().getHostAddress(), randomNum);
		
		Thread.sleep(2000);

		client.processMessage("TESTING MESSAGE IS SUCCESSFULLY EXTCHANGED BETWEEN CLIENT AND SERVER");
		
		Thread.sleep(2000);
	
		Runtime.getRuntime().exit(0);

		
	}*/
	
	

}
