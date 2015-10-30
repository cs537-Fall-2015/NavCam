package webcamcapture;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JFrame {
	

    private JPanel contentPane;
    private static Timer timer;
    private static MyFrame myFrame;
    

  /**
  * Launch the application.
  */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	
                	
                    MyFrame frame = new MyFrame();
                    
                    
                    frame.setVisible(true);
                   
                    
                    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

  /**
  * Create the frame.
  */
    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBounds(100, 100, 700, 500);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        setLayout(new BorderLayout());
        JButton button = new JButton("SOUTH");
        add(button, BorderLayout.WEST);
        
        timer = new Timer();	// Timer helps get current frame of camera  
		timer.scheduleAtFixedRate(new RepaintTask(), new Date(), 50);	// 50ms will trigger the timer
		validate();
		repaint();
  
       // new MyThread().start();
    }
 
    VideoCap videoCap = new VideoCap();
 
    public void paint(Graphics g){
        g = contentPane.getGraphics();
        g.drawImage(videoCap.getOneFrame(), 0, 0, this);
    }
 
    class RepaintTask extends TimerTask{
    	public void run(){
    		//System.out.println("Reporting from RepaintTask. I'm doing the work");
    		myFrame.update();	// This will get the current frame of camera
    		validate();
    		repaint();
    	}
    }
    
    

}