/*package webcamcapture;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.*;
import org.opencv.videoio.*;
import org.opencv.core.*;



public class Main {
	public static void main(String[] args){
		int n=1;
		System.out.println("Hello Opencv");
		System.loadLibrary("opencv_java300");
		VideoCapture cap=new VideoCapture(0);
		if(!cap.isOpened()){
			System.out.println("camera not connect");
			
		}else{
			System.out.println("camera found"+cap.toString());
		}
		Mat frame=new Mat();
		cap.retrieve(frame);
		org.opencv.imgcodecs.Imgcodecs.imwrite("C:\\Jugal\\Courses\\CS537\\"+n+".jpg", frame);
		n++;
		cap.release();
	}
}
*/