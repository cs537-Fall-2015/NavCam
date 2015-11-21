package NavCam.junk.moduleclientserver;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import NavCam.moduleCombineImage.combine;


public class Server {

 
    public static void main(String[] args) throws Exception {
        System.out.println("The capitalization server is running.");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while (true) {
                new Capitalizer(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

 
    private static class Capitalizer extends Thread {
        private Socket socket;
        private int clientNumber;

        public Capitalizer(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
        }

      
        public void run() {
            try {
            	

             
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Hello, you are client #" + clientNumber + ".");
                out.println("Enter a line with only a period to quit\n");
                out.println("***************************************");
         	   out.println("1.combine images");
         	   out.println("2.turn image to gray");
         	   out.println("3.convert image to json");
         	   out.println("4.do 1-3 and send the json to cleint");
         	   out.println("**************************************");
         	   
                while (true) {
                	String message = null;
                    String input = in.readLine();
                   
                    if (input == null || input.equals("."))
                    {
                        break;
                    }
                    if (input.equals("1")) 
                    {
                    	int n =1;
                    	int rows = 2;   //we assume the no. of rows and cols are known and each chunk has equal width and height
                        int cols = 2;
                        int chunks = rows * cols;

                        int chunkWidth, chunkHeight;
                        int type;
                        //fetching image files
                        File[] imgFiles = new File[chunks];

                        imgFiles[0] = new File("src/moduleclientserver/resources/serverimages/lefttop.jpg");
                        imgFiles[1] = new File("src/moduleclientserver/resources/serverimages/righttop.jpg");
                        imgFiles[2] = new File("src/moduleclientserver/resources/serverimages/leftdown.jpg");
                        imgFiles[3] = new File("src/moduleclientserver/resources/serverimages/rightdown.jpg");

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
                        message = "Image concatenated.....";
                        System.out.println("Image concatenated.....");
                        try {
                			ImageIO.write(finalImg, "jpeg", new File("src/moduleclientserver/resources/mixedimage/mixed" +n+".jpg"));
                			n++;
                        } catch (IOException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
                		
                        
                    }
                    
                    if (input.equals("2")) 
                    {
                    	BufferedImage  image;
                    	   int width;
                    	   int height;
                    	   int n=1;
                    	try {
                            File input1 = new File("src/moduleclientserver/resources/mixedimage/mixed"+n+".jpg");
                            image = ImageIO.read(input1);
                            width = image.getWidth();
                            height = image.getHeight();
                            
                            for(int i=0; i<height; i++){
                            
                               for(int j=0; j<width; j++){
                               
                                  Color c = new Color(image.getRGB(j, i));
                                  int red = (int)(c.getRed() * 0.299);
                                  int green = (int)(c.getGreen() * 0.587);
                                  int blue = (int)(c.getBlue() *0.114);
                                  Color newColor = new Color(red+green+blue,
                                  
                                  red+green+blue,red+green+blue);
                                  
                                  image.setRGB(j,i,newColor.getRGB());
                               }
                            }
                            message = "image turn into gray...";
                            System.out.println("image turn into gray...");
                            File ouptut = new File("src/moduleclientserver/resources/grayimage/graymixed" +n+".jpg");
                            ImageIO.write(image, "jpg", ouptut);
                            n++;
                         } catch (Exception e) {}
                    }
                    
                    if (input.equals("3")) 
                    {
                    	ByteArrayOutputStream baos = null;
                		FileOutputStream fileOuputStream = null;
                		try {
                			BufferedImage originalImage = ImageIO.read(new File(
                					"src/moduleclientserver/resources/grayimage/graymixed1.jpg"));
                			baos = new ByteArrayOutputStream();
                			ImageIO.write(originalImage, "jpg", baos);
                			baos.flush();
                			byte[] imageInByte = baos.toByteArray();
                			Gson gson = new GsonBuilder().setPrettyPrinting().create();

                			String base64Encoded = DatatypeConverter.printBase64Binary(imageInByte);
                			FileWriter writer = null;
                			try {
                				writer = new FileWriter("src/moduleclientserver/resources/jsonfiles/graymixed1.json");
                				message = "Json Generated...";
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
                    if (input.equals("4")) 
                    {
                    	int n =1;
                    	int rows = 2;   //we assume the no. of rows and cols are known and each chunk has equal width and height
                        int cols = 2;
                        int chunks = rows * cols;

                        int chunkWidth, chunkHeight;
                        int type;
                        //fetching image files
                        File[] imgFiles = new File[chunks];

                        imgFiles[0] = new File("src/moduleclientserver/resources/serverimages/lefttop.jpg");
                        imgFiles[1] = new File("src/moduleclientserver/resources/serverimages/righttop.jpg");
                        imgFiles[2] = new File("src/moduleclientserver/resources/serverimages/leftdown.jpg");
                        imgFiles[3] = new File("src/moduleclientserver/resources/serverimages/rightdown.jpg");

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
                			ImageIO.write(finalImg, "jpeg", new File("src/moduleclientserver/resources/mixedimage/mixed" +n+".jpg"));
                			
                        } catch (IOException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
                        BufferedImage  image;
                 	   int width;
                 	   int height;
                 	   
                 	try {
                         File input1 = new File("src/moduleclientserver/resources/mixedimage/mixed"+n+".jpg");
                         image = ImageIO.read(input1);
                         width = image.getWidth();
                         height = image.getHeight();
                         
                         for(int i=0; i<height; i++){
                         
                            for(int j=0; j<width; j++){
                            
                               Color c = new Color(image.getRGB(j, i));
                               int red = (int)(c.getRed() * 0.299);
                               int green = (int)(c.getGreen() * 0.587);
                               int blue = (int)(c.getBlue() *0.114);
                               Color newColor = new Color(red+green+blue,
                               
                               red+green+blue,red+green+blue);
                               
                               image.setRGB(j,i,newColor.getRGB());
                            }
                         }
                      
                         System.out.println("image turn into gray...");
                         File ouptut = new File("src/moduleclientserver/resources/grayimage/graymixed" +n+".jpg");
                         ImageIO.write(image, "jpg", ouptut);
                         
                      } catch (Exception e) {}
                 	
                 	ByteArrayOutputStream baos = null;
            		FileOutputStream fileOuputStream = null;
            		try {
            			BufferedImage originalImage = ImageIO.read(new File(
            					"src/moduleclientserver/resources/grayimage/graymixed1.jpg"));
            			baos = new ByteArrayOutputStream();
            			ImageIO.write(originalImage, "jpg", baos);
            			baos.flush();
            			byte[] imageInByte = baos.toByteArray();
            			Gson gson = new GsonBuilder().setPrettyPrinting().create();

            			String base64Encoded = DatatypeConverter.printBase64Binary(imageInByte);
            			FileWriter writer = null;
            			try {
            				writer = new FileWriter("src/moduleclientserver/resources/jsonfiles/graymixed1.json");
            				message = "Json Generated...";
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
            		message = "finished sending";
                    System.out.println("finished sending");
                    }
                    
                    
                    out.println(message);
                    
                }
            } catch (IOException e) {
                log("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }

      
        private void log(String message) {
            System.out.println(message);
        }
    }
}