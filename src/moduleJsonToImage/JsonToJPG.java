package moduleJsonToImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
public class JsonToJPG 

{
	
	public static void main(String[] args)
	{
		File file1 = new File("src/modulejsontoimage/resources/images/image1.jpg");
		File file2 = new File("src/modulejsontoimage/resources/images/image2.jpg");
		
		try {
			
			FileInputStream imageInFile1 = new FileInputStream(file1);
			
			byte imageData1[] = new byte[(int)file1.length()];
			
			imageInFile1.read(imageData1);
			
			FileInputStream imageInFile2 = new FileInputStream(file2);
			
			byte imageData2[] = new byte[(int)file2.length()];
			
			imageInFile2.read(imageData2);
			
			String imageDataString1 = encodeImage(imageData1);
			
			String imageDataString2 = encodeImage(imageData2);
		
			String result = imageDataString1 + imageDataString2;
			System.out.println("jeet" + result);
			
			byte[] imageByteArray1 = decodeImage(result);

	
			
			FileOutputStream imageOutFile = new FileOutputStream("JSON to jpg/jeet1.jpg");
			
			imageOutFile.write(imageByteArray1);
		
			imageInFile1.close();
			

			imageInFile2.close();
			
			imageOutFile.close();
			
			System.out.println("Image Successfully Manipulated!");
			
		} 
		catch (FileNotFoundException e) 
		
		{
			
			System.out.println("Image not found" + e);
			
		}
		catch (IOException ioe) 
		{
			
			System.out.println("Exception while reading the Image " + ioe);
			
		}
		

	}
	
	/**
	 * Encodes the byte array into base64 string
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray)
	{		
		return Base64.encodeBase64URLSafeString(imageByteArray);		
	}
	
	/**
	 * Decodes the base64 string into byte array
	 * @param imageDataString - a {@link java.lang.String} 
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString)
	{		
		return Base64.decodeBase64(imageDataString);
	}
	
	
	

}
