package webcamcapture;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.opencv.core.Core;

public class MainFrame extends JFrame implements ActionListener {
	
	private MyFrame myFrame;
	Timer timer;
	private JPanel captureView;

	JPanel buttonPane;


	public MainFrame()
	{
		captureView = new JPanel();

		// This will create the buttons
		buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(1,2));
		
		 JButton captureButton = new JButton("Capture"); 
		 captureButton.addActionListener(this);
		 
		 JButton TURN_OFF_Button = new JButton("TURN OFF"); 
		 TURN_OFF_Button.addActionListener(this);
		 
		 buttonPane.add(captureButton);
		 buttonPane.add(TURN_OFF_Button);

		 
		
		
		// My Frame is the JLabel, which has background image of current camera frame
		myFrame = new MyFrame();
		myFrame.setPreferredSize(new Dimension(800, 600));
		captureView.setLayout(new BorderLayout());
		captureView.add(myFrame, BorderLayout.CENTER);
		captureView.add(buttonPane, BorderLayout.SOUTH);
		
		
		
		add(captureView, BorderLayout.CENTER);
		timer = new Timer();	// Timer helps get current frame of camera  
		timer.scheduleAtFixedRate(new RepaintTask(), new Date(), 50);	// 50ms will trigger the timer
		validate();
		repaint();
		
		setBounds(100, 100, 800, 600);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		
		
		System.out.println("The camera is TURNED ON" );


	}
	

	
	
	class RepaintTask extends TimerTask{
		public void run(){
			//System.out.println("Reporting from RepaintTask. I'm doing the work");
			myFrame.update();	// This will get the current frame of camera
			validate();
			repaint();
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);	// Load OpenCV stuff
		
		if(e.getActionCommand().equals("Capture"))
		{
			boolean gotNewImage = myFrame.capture("camera.jpg");
			
			if (gotNewImage) {
				File tmpFile = new File("camera.jpg");
				
				System.out.println("The system captured an photo with the name camera.jpg" );

				
			} 
			
			
		}
		else if(e.getActionCommand().equals("TURN OFF"))
		{
			timer.cancel();
			timer.purge();
			myFrame.release();
			setVisible(false);
			
			System.out.println("The camera is TURNED OFF" );
			
			MainView mainView = new MainView();

			
		//	Runtime.getRuntime().exit(0);

		}
	
	}
}



