package webcamcapture;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView  extends JFrame implements ActionListener {

	JPanel buttonPane;

	public  MainView()
	{
		setBounds(100, 100, 800, 600);
		setTitle("NavCam");

		buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(1,3));
		
		 JButton TURN_ON_Button = new JButton("TURN ON"); 
		 TURN_ON_Button.addActionListener(this);
		 
		 JButton aboutButton = new JButton("About NavCam"); 
		 aboutButton.addActionListener(this);
		 
		 JButton exitButton = new JButton("Exit"); 
		 exitButton.addActionListener(this);
		 
		 buttonPane.add(TURN_ON_Button);
		 buttonPane.add(aboutButton);
		 buttonPane.add(exitButton);
		 
		 
		 ImageIcon imageIcon = new ImageIcon("NavCamData/NavCam.png");
		 JLabel label = new JLabel(imageIcon);
		 
		add(buttonPane, BorderLayout.SOUTH);
		add(label, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		System.out.println("Welcome, to NavCam Main View" );
	 
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getActionCommand().equals("TURN ON"))
		{
		
			setVisible(false);
			MainFrame frame = new MainFrame();

			
		}
		else if(e.getActionCommand().equals("Exit"))
		{
		
			setVisible(false);
			
			System.out.println("The NavCam ShutDown" );
			
			Runtime.getRuntime().exit(0);

		}else if(e.getActionCommand().equals("About NavCam"))
		{
			setVisible(false);
			
			AboutFrame about = new AboutFrame();
			
			

		}
		
	}

}
