package NavCam.moduleNavCamClick;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class AboutFrame extends JFrame implements ActionListener{
	
	
	public AboutFrame()
	{
		
		setBounds(100, 100, 800, 600);
		setTitle("About - NavCam");
		
		JEditorPane tmpEditorPane = new JEditorPane();
		HTMLEditorKit aboutKit = new HTMLEditorKit();
		StyleSheet aboutSS = aboutKit.getStyleSheet();
		
		aboutSS.addRule("body {font-size: 14px;color:#000; font-family:times; margin: 4px; }");
		aboutSS.addRule("h2 {color: #D8D8D8; background-color: #00000; }");
		aboutSS.addRule("div {background-color: #00000; border-style: solid; border-color: #00000;}");
		
		Document aboutDoc = aboutKit.createDefaultDocument();
		tmpEditorPane.setEditorKit(aboutKit);
		tmpEditorPane.setDocument(aboutDoc);
		String aboutStr = "<html>\n"
				+ "<head>\n</head>\n"
				+ "<body>\n" + "<h2><b>NavCam<b></h2><br>Mounted on the mast (the rover neck and head), these black-and-white cameras use visible light to gather panoramic, three-dimensional (3D) imagery. The Navcam is a stereo pair of cameras, each with a 45-degree field of view to support ground navigation planning by scientists and engineers. They work in cooperation with the Hazcams by providing a complementary view of the terrain.<br><h2>Created By:</h2><br>Ahmed Al Obaidi<br>Manan Patel<br>Krunal Patel<br>Raylong Ma"
				+ "</p>" + "<br><br>" + "</body>\n</html>";
		tmpEditorPane.setText(aboutStr);
		tmpEditorPane.setEditable(false);
		JScrollPane tmpScrollPane = new JScrollPane(tmpEditorPane);
		JPanel aboutView = new JPanel();
		aboutView.setLayout(new BorderLayout());
		
		 JButton backButton = new JButton("Back"); 
		 backButton.addActionListener(this);

		aboutView.add(backButton, BorderLayout.SOUTH);
		aboutView.add(tmpScrollPane, BorderLayout.CENTER);

		
		add(aboutView);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		System.out.println("Welcome, to About NavCam Frame" );

		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
			setVisible(false);
			
			MainView mainView = new MainView();	

		
		
		
	}

}
