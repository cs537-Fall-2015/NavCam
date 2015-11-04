package convert4image;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

/**
 * @desc Image manipulation - Conversion
 * 
 * @filename ImageManipulation.java
 * @author <a href="mailto:jeeva@myjeeva.com">Jeevanandam Madanagopal</a>
 * @copyright &copy; 2010-2012 www.myjeeva.com
 */
public class convertion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file1 = new File("JSON to jpg/DD18.jpg");
		File file2 = new File("JSON to jpg/DD19.jpg");
		File file3 = new File("JSON to jpg/DD20.jpg");
		File file4 = new File("JSON to jpg/DD21.jpg");
		try {
			/*
			 * Reading a Image file from file system
			 */
			FileInputStream imageInFile1 = new FileInputStream(file1);
			byte imageData1[] = new byte[(int)file1.length()];
			imageInFile1.read(imageData1);
			FileInputStream imageInFile2 = new FileInputStream(file2);
			byte imageData2[] = new byte[(int)file2.length()];
			imageInFile2.read(imageData2);
			/*
			 * Converting Image byte array into Base64 String 
			 */
			String imageDataString1 = encodeImage(imageData1);
			String imageDataString2 = encodeImage(imageData2);
		
			/*
			 * Converting a Base64 String into Image byte array 
			 */
			String result = imageDataString1 + imageDataString2;
			byte[] imageByteArray1 = decodeImage(result);

	
			/*
			 * Write a image byte array into file system  
			 */
			FileOutputStream imageOutFile = new FileOutputStream("JSON to jpg/img1.jpg");
			imageOutFile.write(imageByteArray1);
		
			imageInFile1.close();
			imageInFile2.close();
			imageOutFile.close();
			
			System.out.println("Image Successfully Manipulated!");
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}

	}
	
	/**
	 * Encodes the byte array into base64 string
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray){		
		return Base64.encodeBase64URLSafeString(imageByteArray);		
	}
	
	/**
	 * Decodes the base64 string into byte array
	 * @param imageDataString - a {@link java.lang.String} 
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}

}
