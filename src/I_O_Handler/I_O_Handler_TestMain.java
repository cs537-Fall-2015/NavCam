package I_O_Handler;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;


public class I_O_Handler_TestMain { 
	
	private static Server server;
	private static Client client;
	
	
	public static void main(String[] args) throws IOException, InterruptedException {

		Random rand = new Random();
	    int randomNum = rand.nextInt((65535 - 1) + 1) + 1;

	
		new Thread(new Runnable() {@Override
			public void run() {
				try {
					
					server = new Server(randomNum);					

				} catch (IOException e) {}
			}
		}).start();
		
		
		Thread.sleep(1000);
		
		System.out.println("Waiting for incomming connection ...");
		
		Thread.sleep(3000);
		
		client = new Client(InetAddress.getLocalHost().getHostAddress(), randomNum);
		
		Thread.sleep(2000);

		client.processMessage("TESTING MESSAGE IS SUCCESSFULLY EXTCHANGED BETWEEN CLIENT AND SERVER");
		
		Thread.sleep(2000);
	
		Runtime.getRuntime().exit(0);

		
	}

}
