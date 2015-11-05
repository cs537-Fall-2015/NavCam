package module7;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;




import java.util.Random;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import static com.googlecode.javacv.cpp.opencv_highgui.*;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import generic.RoverServerRunnable;
public class ModuleSevenServer extends RoverServerRunnable{
	
	
	 public void GrayScale(String name) {
		   
	      try {
	         File input = new File(name);
	        BufferedImage  image = ImageIO.read(input);
	         int width = image.getWidth();
	         int height = image.getHeight();
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               Color c = new Color(image.getRGB(j, i));
	               int red = (int)(c.getRed() * 0.299);
	               int green = (int)(c.getGreen() * 0.587);
	               int blue = (int)(c.getBlue() *0.114);
	               Color newColor = new Color(red+green+blue,
	               
	               red+green+blue,red+green+blue);
	               
	               image.setRGB(j,i,newColor.getRGB());
	            }
	         }
	         
	         File ouptut = new File(name);
	         ImageIO.write(image, "jpg", ouptut);
	         
	      } catch (Exception e) {}
	   }
	
	
	int n;
	public ModuleSevenServer(int port) throws IOException {
		super(port);
	}
	@Override
	public void run(){
		MyClassSeven modulesevenobj=new MyClassSeven();
		
		try{
			while(true){
				System.out.println("Waiting for client....");
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();

				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());

				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("\nModule 7 Server: Message Received from Client - "+ message.toUpperCase());
				// create ObjectOutputStream object

				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				// write object to Socket
				//outputToAnotherObject.writeObject("\nModule 7 Server response - REQUEST PROCESSED: - " + message);
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();

				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if(message.equalsIgnoreCase("NCAM_TURN_ON")){
					modulesevenobj.power_jason(4.4);
					//if(message.equalsIgnoreCase("power_available")){
					
					System.out.println("\nCOMMAND : NCAM_TURN_ON");
					System.out.println("----NAV CAM ON----");
					modulesevenobj.power_jason(4.4);
					Thread.sleep(5000);
					if(message.equalsIgnoreCase("NCAM_CAPTURE"));{
						
						System.out.println("\nCOMMAND : NCAM_CAPTURE");
						System.out.println("----Use dummy images----");
						Random rand= new Random();
						int randomNum = (rand.nextInt((10 - 1) + 1) + 1);
						//System.out.println("------------------------"+randomNum);
						modulesevenobj.convert_to_json("C:\\Jugal\\Courses\\CS537\\Capture Set\\"+randomNum+".jpg");
						System.out.println("----Random image number"+randomNum+" selected and stored in JSON---- ");
						Thread.sleep(5000);
						Thread.sleep(5000);
					}
					
					
					if(message.equalsIgnoreCase("NCAM_CLICK"));{
						
						System.out.println("\n\nCOMMAND : NCAM_CLICK");
						System.out.println("----Click a Picture using WebCam----");
						Random rand= new Random();
						int n = (rand.nextInt((100 - 1) + 1) + 1);
						Thread.sleep(5000);
						Thread.sleep(5000);
						System.loadLibrary("opencv_java300");
						VideoCapture cap=new VideoCapture(0);
						if(!cap.isOpened()){
							System.out.println("----camera not connect----");

						}else{
							System.out.println("----Camera Found----");
						}
						Mat frame=new Mat();
						cap.retrieve(frame);
						String name="Click Set\\"+n+".jpg";
						org.opencv.imgcodecs.Imgcodecs.imwrite(name, frame);
						
						GrayScale(name);
						modulesevenobj.convert_to_json("Click Set/"+n+".jpg");
						System.out.println("----Picture clicked and stored in JSON----");
						
						cap.release();
						Thread.sleep(5000);
						Thread.sleep(5000);
						System.out.println("\n\nCOMMAND : NCAM_TURN_OFF");
						System.out.println("----NAV CAM OFF----");

					}
					
					
					if (message.equalsIgnoreCase("NCAM_TURN_OFF")){
						
						break;
					}
					//}else{
					//System.out.println("Power not available");
					//	break;
					//}
				}else{
					System.out.println("Cam not turned ON");
					break;
				}
			}//end of while
			
			System.out.println("Server: Shutting down Socket server 7!!");
			// close the ServerSocket object
			closeAll();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error:" + error.getMessage());
		}

	}
}