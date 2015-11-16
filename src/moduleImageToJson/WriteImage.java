package moduleImageToJson;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WriteImage {
	
	
	public WriteImage()
	{
		ByteArrayOutputStream baos = null;
		FileOutputStream fileOuputStream = null;
		try {
			BufferedImage originalImage = ImageIO.read(new File(
					"src/moduleImageToJson/resources/Images/graymixed1.jpg"));
			baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			String base64Encoded = DatatypeConverter.printBase64Binary(imageInByte);
			FileWriter writer = null;
			try {
				writer = new FileWriter("src/moduleImageToJson/resources/JsonFiles/graymixed1.json");
			System.out.println("Json Generated...");
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
