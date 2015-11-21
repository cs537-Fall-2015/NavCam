package NavCam.moduleNavCamClick;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class VideoCap {
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    VideoCapture cap;
    Mat2Image mat2Img = new Mat2Image();
    int camNumber;

    VideoCap(int camNumber){
    	this.camNumber = camNumber;
        cap = new VideoCapture();
        cap.open(camNumber);
    } 
  
    void release(){
    	if (cap != null)
    		cap.release();
    }
    boolean capture(String fileName){
    	if (cap.read(mat2Img.mat)){
    		Highgui.imwrite(fileName, mat2Img.mat);
    		return true;
    	}
    	return false;
    }
    BufferedImage getOneFrame() {
    	
        cap.read(mat2Img.mat);
     
        return mat2Img.matToBufferedImage(mat2Img.mat);
    }

	public int getCamNumber() {
		return camNumber;
	}

	public void setCamNumber(int camNumber) {
		this.camNumber = camNumber;
	}


    
    
    
  }