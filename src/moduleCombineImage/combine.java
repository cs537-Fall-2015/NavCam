package moduleCombineImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class combine 
{
	private int n =1;
	public void run()
	{
		
		int rows = 2;   //we assume the no. of rows and cols are known and each chunk has equal width and height
        int cols = 2;
        int chunks = rows * cols;

        int chunkWidth, chunkHeight;
        int type;
        //fetching image files
        File[] imgFiles = new File[chunks];

        imgFiles[0] = new File("src/modulecombineimage/resources/originalimages/lefttop.jpg");
        imgFiles[1] = new File("src/modulecombineimage/resources/originalimages/righttop.jpg");
        imgFiles[2] = new File("src/modulecombineimage/resources/originalimages/leftdown.jpg");
        imgFiles[3] = new File("src/modulecombineimage/resources/originalimages/rightdown.jpg");

       //creating a bufferd image array from image files
        BufferedImage[] buffImages = new BufferedImage[chunks];
        for (int i = 0; i < chunks; i++) {
            try {
				buffImages[i] = ImageIO.read(imgFiles[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        type = buffImages[0].getType();
        chunkWidth = buffImages[0].getWidth();
        chunkHeight = buffImages[0].getHeight();

        //Initializing the final image
        BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*rows, type);
        

        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
                num++;
            }
        }
        System.out.println("Image concatenated.....");
        try {
			ImageIO.write(finalImg, "jpeg", new File("src/modulecombineimage/resources/mixedimages/mixed" +n+".jpg"));
			n++;
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
