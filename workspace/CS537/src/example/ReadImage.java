package example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadImage {
	public static void main(String[] args)  {
		jsontojpg("Image1");
		jsontojpg("Image2");
		

	}
	public static void jsontojpg(String imagestring){
		Random rand= new Random();
		int n = (rand.nextInt((100 - 1) + 1) + 1);
		// You need to define this filepath yourself
		// This is where the file will be written to
		// In this example it is written to my desktop
		// If Example.json doesn't exist it will be created
		String myFilePath = "C:/Jugal/Courses/CS537/JSON generated/"+imagestring+".json";
		
		// JSONParser is used to parse the data
		JSONParser parser = new JSONParser();
		
		try {
			String image = (String) parser.parse(new FileReader(myFilePath));
		
			byte[] imageInByte = DatatypeConverter.parseBase64Binary(image);
		
			FileOutputStream fileOuputStream = new FileOutputStream(
				    "C:/Jugal/Courses/CS537/JSON to jpg/DD"+n+".jpg");
			    fileOuputStream.write(imageInByte);	    
			    System.out.println("Conversion completed");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
