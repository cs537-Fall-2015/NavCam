package NavCam.testmainNavCam;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

import NavCam.NavCamClient;
import NavCam.NavCamServer;


public class NavCam_TestMain {

	private static NavCamServer server;
	private static NavCamClient client;
	
	
	public static void main(String[] args) throws IOException, InterruptedException {

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

		
	}

}
