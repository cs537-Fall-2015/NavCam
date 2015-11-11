package I_O_Handler;



import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {
	private ServerSocket ss;
	
	public Server(int port) throws IOException {
		listen(port);
	}
	private void listen(int port) throws IOException {
		
		
		InetAddress add = InetAddress.getLocalHost();

		ss = new ServerSocket(port,0,add);
		

	
		System.out.println("The Server is Running on " + ss.getInetAddress().getHostAddress()+"/"+ss.getLocalPort());
		while (true) {
			Socket s = ss.accept();
			new ServerThread(this, s);
		}
	}


}