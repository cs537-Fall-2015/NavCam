package main;

import generic.RoverThreadHandler;

import java.io.IOException;

import json.Constants;
import module1.ModuleOneServer;
import module2.ModuleTwoClient;
import module2.ModuleTwoServer;
import module7.ModuleSevenClient1;
import module7.ModuleSevenServer;


public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_one = Constants.PORT_ONE;
		int port_two = Constants.PORT_TWO;
		int port_seven = Constants.PORT_SEVEN;
		
		try {
			
			
			ModuleSevenServer serverSeven = new ModuleSevenServer(port_seven);
			Thread server_7 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverSeven);
			 
			server_7.start();
			
			// Client 7 thread
			ModuleSevenClient1 clientSeven = new ModuleSevenClient1(port_seven, null); // notice port_one
			Thread client_7 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientSeven);
												
			client_7.start();
			
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}