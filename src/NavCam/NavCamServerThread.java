package NavCam;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import NavCam.NavCamServer;
public class NavCamServerThread extends Thread {

	private NavCamServer server;
	private Socket socket;
	
	public NavCamServerThread(NavCamServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
		start();
	}
	@Override
	public void run() {
		try {
			DataInputStream din = new DataInputStream(socket.getInputStream());
			while (true) {
				String message = din.readUTF();
				System.out.println("Server Received from Client:" );
				System.out.println(message);
			}
		} catch (EOFException ie) {
		} catch (IOException ie) {
		} finally {
			
			this.interrupt();
			this.stop();

		}
	}
	//
}