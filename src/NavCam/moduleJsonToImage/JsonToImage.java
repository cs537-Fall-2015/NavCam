package NavCam.moduleJsonToImage;


import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonToImage
{
	
	public JsonToImage(String jsonFile)
	{
		Random rand= new Random();
		int n = (rand.nextInt((100 - 1) + 1) + 1);
		// You need to define this filepath yourself
		// This is where the file will be written to
		// In this example it is written to my desktop
		// If Example.json doesn't exist it will be created
		String myFilePath = "src/NavCam/modulejsontoimage/resources/jsonfiles/"+jsonFile+".json";
		
		// JSONParser is used to parse the data
		JSONParser parser = new JSONParser();
		
		try {
			String image = (String) parser.parse(new FileReader(myFilePath));
		
			byte[] imageInByte = DatatypeConverter.parseBase64Binary(image);
		
			FileOutputStream fileOuputStream = new FileOutputStream(
				    "src/NavCam/modulejsontoimage/resources/images/"+jsonFile+".jpg");
			    fileOuputStream.write(imageInByte);	    
			    System.out.println("The Json File >>"+ jsonFile+" converted to the image "+ jsonFile+".jpg");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
