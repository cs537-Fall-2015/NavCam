package main;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import generic.RoverThreadHandler;

import java.io.IOException;

import json.Constants;
import module1.ModuleOneClient;
import module1.ModuleOneServer;
import module2.ModuleTwoClient;
import module2.ModuleTwoServer;
import hazcam.*;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		//int port_one = Constants.PORT_ONE;
		int port_two = Constants.PORT_TWO;
		int port_hcam=Constants.PORT_HAZCAM;
		
		try {
			
			// create a thread for module one
			HazcamServer hserver = new HazcamServer(port_hcam);
			Thread server_hazcam = RoverThreadHandler.getRoverThreadHandler().getNewThread(hserver);
			
			// create a thread for module two
			ModuleTwoServer serverTwo = new ModuleTwoServer(port_two);
			Thread server_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverTwo);
			
			// each server begins listening
			server_hazcam.start();
			server_2.start();
			
			// The following commands are examples of sending data: 
			// from module 1 client to module 2 server
			// and from module 2 client to module 2 server
			
			// client one server sending messages to server two
		//	ModuleOneClient clientOne = new ModuleOneClient(port_two, null); // notice port_two
		//	Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
			
			// client two server sending messages to server one
			TempClient Tempclient = new TempClient(port_hcam, null); // notice port_one
			Thread client_Temp = RoverThreadHandler.getRoverThreadHandler().getNewThread(Tempclient);
			
			// start the thread which communicates through sockets
			//client_1.start();
			client_Temp.start();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
