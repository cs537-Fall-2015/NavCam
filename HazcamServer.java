package hazcam;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import generic.RoverServerRunnable;

public class HazcamServer extends RoverServerRunnable {

	public HazcamServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		HazcamClass moduleOneClass = new HazcamClass(1);
		
		try {

			while (true) {
				
				System.out.println("Hazcam Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Hazcam Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("Hazcam Server response Hi Client - " + message);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(moduleOneClass);
				
				outputToAnotherObject.writeObject(jsonString);
				
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase("MODULE_PRINT")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server HazCam>");
					System.out.println("This is module " + Constants.FIFTEEN + "'s object at the start");
					moduleOneClass.printObject();
					System.out.println("<Server HazCam>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("HAZCAM_FRONT_CAMERA1__IMAGE")) {
					moduleOneClass.addOne();
					moduleOneClass.changeBoolean();
					moduleOneClass.changeDouble();
					moduleOneClass.changeLong();
					moduleOneClass.changeString();
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleOneClass, Constants.ONE);
					System.out.println("");
					System.out.println("<HazCam Server>");
					//moduleOneClass.printObject();
					System.out.println("HCAM_FRONT_CAM1_ON (to turn on the front camera1)");
					Thread.sleep(1000);
					System.out.println("HCAM_FRONT_CAM1_CAPT (To take a picture from front cam1)");
					Thread.sleep(1000);
					System.out.println("HCAM_FRONT_CAM1_OFF (to turn off the front camera1)");
					Thread.sleep(1000);	
					System.out.println("<HazCam Server>");
					System.out.println("");
				}/*
				else if(message.equalsIgnoreCase("HAZCAM_FRONT_CAMERA2__IMAGE")) {
					moduleOneClass.addOne();
					moduleOneClass.changeBoolean();
					moduleOneClass.changeDouble();
					moduleOneClass.changeLong();
					moduleOneClass.changeString();
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleOneClass, Constants.ONE);
					System.out.println("");
					System.out.println("<Server One>");
					moduleOneClass.printObject();
					System.out.println("<Server One>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("HAZCAM_REAR_CAMERA1__IMAGE")) {
					moduleOneClass.addOne();
					moduleOneClass.changeBoolean();
					moduleOneClass.changeDouble();
					moduleOneClass.changeLong();
					moduleOneClass.changeString();
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleOneClass, Constants.ONE);
					System.out.println("");
					System.out.println("<Server One>");
					moduleOneClass.printObject();
					System.out.println("<Server One>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("HAZCAM_REAR_CAMERA2__IMAGE")) {
					moduleOneClass.addOne();
					moduleOneClass.changeBoolean();
					moduleOneClass.changeDouble();
					moduleOneClass.changeLong();
					moduleOneClass.changeString();
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleOneClass, Constants.ONE);
					System.out.println("");
					System.out.println("<Server One>");
					moduleOneClass.printObject();
					System.out.println("<Server One>");
					System.out.println("");
				}*/
				else if(message.equalsIgnoreCase("CLIENT_MODULE_GET")) {
					// The server reads another a JSON Object in memory
					GlobalReader JSONReader = new GlobalReader(Constants.TWO);
					JSONObject thatOtherObject = JSONReader.getJSONObject();
					
					// Integers are passed as longs
					Long myLong = (Long) thatOtherObject.get("myInteger");
					
					// Pass the long back into an integer
					Integer myInteger = new Integer(myLong.intValue());
					String myString = (String) thatOtherObject.get("myString");
					
					System.out.println("");
					System.out.println("<Start> Hazcam Server Receiving <Start>");
					System.out.println("===========================================");
					System.out.println("This is Class " + Constants.TWO + "'s object ");
					System.out.println("myInteger = " + myInteger);
					System.out.println("myString = " + myString);
					System.out.println("===========================================");
					System.out.println("<End> Hazcam Server Receiving <End>");
					System.out.println("");
				}
			}
			System.out.println("Server: Shutting down Socket Hazcam!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error: " + error.getMessage());
		}

	}

}
