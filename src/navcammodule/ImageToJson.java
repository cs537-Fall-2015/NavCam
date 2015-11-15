package navcammodule;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import javax.json.*;
import org.json.simple.JSONObject;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.sun.media.vfw.BitMapInfo;
import org.apache.commons.codec.binary.Base64;;

public class ImageToJson {

	/*private String getStringFromBitmap(BufferedImage bitmapPicture) {
		
		 final int COMPRESSION_QUALITY = 100;
		 String encodedImage;
		 ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
		 bitmapPicture.compress(BufferedImage.CompressFormat.PNG, COMPRESSION_QUALITY,
		 byteArrayBitmapStream);
		 byte[] b = byteArrayBitmapStream.toByteArray();
		 encodedImage = Base64.encodeToString(b, Base64.getEncoder());
		 return encodedImage;
		 }*/
	
	 public static String encodeImage(byte[] imageByteArray) {
	       return Base64.encodeBase64URLSafeString(imageByteArray);
		
	    }
	 
	    public static byte[] decodeImage(String imageDataString) {
	        return Base64.decodeBase64(imageDataString);
	    }
	
	private String getStringFromImage(File file)
	{
		try {            
			
            // Reading a Image file from file system
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
 
            // Converting Image byte array into Base64 String
            String imageDataString = encodeImage(imageData);
 
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = decodeImage(imageDataString);
 
            // Write a image byte array into file system
            FileOutputStream imageOutFile = new FileOutputStream(" ");
 
            imageOutFile.write(imageByteArray);
 
            imageInFile.close();
            imageOutFile.close();
            return imageDataString;
 
            
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
		return null;
		
	}
	
	
	private static JsonObject jsonFromString(String jsonObjectStr) {
		
	    JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
	    JsonObject object = jsonReader.readObject();
	    jsonReader.close();

	    return object;
	}

	/*public static void main(String[] args)
	{
		String encodedImage = getStringFromBitmap(bitmapPicture);
		JSONObject jsonObj = new JSONObject("{\"image\":\" + encodedImage + \"}");
	}*/
}
