package moduleNavCamCapture;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import moduleImageToJson.ImageToJSON;

public class NavCamCapture {
	
	ArrayList<String> filesNames = new ArrayList<String>();
	
	String selectedImage = "";
	
	public NavCamCapture() throws InterruptedException
	{
		File folder = new File("src/moduleNavCamCapture/resources/capturedImages");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	filesNames.add(file.getName().replaceFirst("[.][^.]+$", ""));
		    }
		}
		
		
		Random r = new Random();
		int Low = 0;
		int High = filesNames.size()-1;
		int Result = r.nextInt(High-Low) + Low;
		
		
		
		System.out.println("Random Image has been selected >>> "+filesNames.get(Result));
		 setSelectedImage(filesNames.get(Result));
		Thread.sleep(1000);
		
		System.out.println("Generateing the JsonFile for >>>"+ filesNames.get(Result)+" ...");
		ImageToJSON toJson = new ImageToJSON(filesNames.get(Result));
		Thread.sleep(1000);

		System.out.println("JsonFile generated for >>>"+ filesNames.get(Result));
		Thread.sleep(1000);



	}

	public String getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(String selectedImage) {
		this.selectedImage = selectedImage;
	}
	
	
	

}
