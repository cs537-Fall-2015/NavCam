package moduleImageToJson;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
<<<<<<< HEAD:src/moduleImageToJson/ImageToJson.java
=======
import javax.json.*;
>>>>>>> 923f5e74b95ec2366bdb78cd60173a4a7e240cb0:src/navcammodule/ImageToJson.java
import org.json.simple.JSONObject;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.sun.media.vfw.BitMapInfo;


import org.apache.commons.codec.binary.Base64;;

public class ImageToJson {

	
	 public static void main(String[] args){
	    	ImageToJson i = new ImageToJson();
	    	
	    	//File file = new File("C:/1.JPG");
	    	String output=i.getStringFromImage(new File("C:/Users/Public/Videos/Dell/Wildlife.jpg"));
	    	System.out.println(output);
<<<<<<< HEAD:src/moduleImageToJson/ImageToJson.java
	    	
=======
>>>>>>> 923f5e74b95ec2366bdb78cd60173a4a7e240cb0:src/navcammodule/ImageToJson.java
	    }
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
            FileOutputStream imageOutFile = new FileOutputStream("C:/Users/Public/Videos/Dell/Wildlifeafter1.jpg");
 
            imageOutFile.write(imageByteArray);
 
            imageInFile.close();
            imageOutFile.close();
            return imageDataString;
 
            
        } catch (FileNotFoundException e) {
            System.out.println("Image not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
		return null;
		
	}
	
	
	/*private static JsonObject jsonFromString(String jsonObjectStr) {
		
	    JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
	    JsonObject object = jsonReader.readObject();
	    jsonReader.close();
<<<<<<< HEAD:src/moduleImageToJson/ImageToJson.java
=======

	    return object;
	}*/
>>>>>>> 923f5e74b95ec2366bdb78cd60173a4a7e240cb0:src/navcammodule/ImageToJson.java

	    return object;
	}*/

	
}
