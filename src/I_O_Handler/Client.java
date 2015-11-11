package I_O_Handler;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {
	
	private Socket socket;
	private DataOutputStream dout;
	private DataInputStream din;

	public Client(String host, int port) {



		try {
			socket = new Socket(host, port);
			System.out.println("connected to " + socket.getInetAddress().getHostAddress()+"/"+socket.getPort());
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			
			new Thread(this).start();

			
		} catch (IOException ie) {
			System.out.println(ie);
		}
	}
	
	public void processMessage(String message) {
		try {
			dout.writeUTF(message);
		} catch (IOException ie) {
			System.out.println(ie);
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String message = din.readUTF();
			}
		} catch (IOException ie) {
			System.out.println(ie);
		}
	}
	

}
