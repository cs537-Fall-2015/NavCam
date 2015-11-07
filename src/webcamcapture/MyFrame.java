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
    
  
	BufferedImage img;
    public MyFrame() {
       setPreferredSize(new Dimension(380, 380));
       videoCap = new VideoCap();
    }
    
    public boolean capture(String fileName){
    	return videoCap.capture(fileName);
    }
 
    VideoCap videoCap;
 
    public void release(){
    	if (videoCap != null)
    		videoCap.release();
    }
    
    public void update(){
    	if (videoCap == null)
        	videoCap = new VideoCap();
        
        try {
        setIcon(new ImageIcon(videoCap.getOneFrame()));
        repaint();
        } catch (Exception e) {}
    }
  
 
}