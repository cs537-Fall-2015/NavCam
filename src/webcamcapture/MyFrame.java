package webcamcapture;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class MyFrame extends JLabel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    VideoCap videoCap;

    
  
	BufferedImage img;
    public MyFrame() {
       setPreferredSize(new Dimension(380, 380));
       videoCap = new VideoCap(0);
    }
    
    public boolean capture(String fileName){
    	return videoCap.capture(fileName);
    }
 
 
    public void release(){
    	if (videoCap != null)
    		videoCap.release();
    }
    
    public void update(){
    	if (videoCap == null)
        	videoCap = new VideoCap(videoCap.getCamNumber());
        
        try {
        setIcon(new ImageIcon(videoCap.getOneFrame()));
        repaint();
        } catch (Exception e) {}
    }
    
    public void switchToLeftTop()
    {
    	
    	videoCap = new VideoCap(0);

    }
    
    public void switchToRightTop()
    {
    	videoCap = new VideoCap(1);
 
    }
    
    public void switchToLeftDown()
    {

    	videoCap = new VideoCap(2);
    	

    }
    
    public void switchToRightDown()
    {

    	videoCap = new VideoCap(3);
  
    }
  
 
}