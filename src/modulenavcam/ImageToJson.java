package modulenavcam;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import org.json.simple.JSONObject;

public class ImageToJson {
	
	private String getStringFromBitmap(Bitmap bitmapPicture) {
		
		 final int COMPRESSION_QUALITY = 100;
		 String encodedImage;
		 ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
		 bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
		 byteArrayBitmapStream);
		 byte[] b = byteArrayBitmapStream.toByteArray();
		 encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
		 return encodedImage;
		 }

	public static void main(String[] args)
	{
		String encodedImage = getStringFromBitmap(bitmapPicture);
		JSONObject jsonObj = new JSONObject("{\"image\":\" + encodedImage + \"}");
	}
	
}
