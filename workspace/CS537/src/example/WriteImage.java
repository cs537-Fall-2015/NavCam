package example;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WriteImage {
	public static void main(String[] args) {

		ByteArrayOutputStream baos = null;
		FileOutputStream fileOuputStream = null;
		try {
			BufferedImage originalImage = ImageIO.read(new File(
					"C:/Users/Public/Pictures/Sample Pictures/Desert.jpg"));
			baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			/*// convert array of bytes into modified file again
			fileOuputStream = new FileOutputStream(
					"C:/Users/kisha_000/Desktop/my work/imagex1.jpg");
			fileOuputStream.write(imageInByte);	 */  
			//byte[] originalBytes = new byte[] { 1, 2, 3, 4, 5};
			String base64Encoded = DatatypeConverter.printBase64Binary(imageInByte);
			FileWriter writer = null;
			try {
				writer = new FileWriter("C:/Jugal/Courses/CS537/JSON generated/Image.json");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Object is converted to a JSON String
			// Object is converted to a JSON String
			String jsonString = gson.toJson(base64Encoded);
			
			
			// Write the file
			try {
				writer.write(jsonString);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Close the Writer
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				baos.close();
				//fileOuputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 

		}

	}
}
