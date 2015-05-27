package com.sister.kelompok5;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.imageio.ImageIO;

class ThreadWorker extends Thread {
	private String worker;
	private int index;

	ThreadWorker(String server, int idx) {
		this.worker = server;
		this.index = idx;
		
		System.out.println("Sending to " + worker);
	}
	
	public void run() {
        try {			
            /*
             * set to RMI server address and port
             * looking for service named "Echo", call remote method
             */
            Registry registry = LocateRegistry.getRegistry(worker, 5000);					
            MessageInterface message = (MessageInterface) registry.lookup("ConvertColor");
            
            // Full file path
            String imagePath = "";
            File path = new File(imagePath);
            
            // File list
            String[] files = path.list();
            
            // Number of files
            int numOfFiles = files.length;
            
            // Initialize byte array
            byte[] images;
            
            for (int i = index - 1; i < numOfFiles; i+=2) {
            	// Image file will send to server
            	String fileIndex = files[i];
            	File sendFile = new File(imagePath + "/" + fileIndex);
            	
            	// Using regex to remove file extensions
            	String fileName = fileIndex.replaceFirst("[.][^.]+$", "");
            	
            	// Get file extesions
            	String fileExtension = fileIndex.substring(fileIndex.lastIndexOf('.')+1);
            	
            	BufferedImage img = ImageIO.read(sendFile);
            	
            	ByteArrayOutputStream baos = new ByteArrayOutputStream();
            	ImageIO.write(img, fileExtension, baos);
            	images = new byte[baos.size()];
            	images = baos.toByteArray();
            	
            	int fileNumber = i + 1;
            	
            	// Call method to start converting color
            	byte[] imageOut = message.Convert(images, fileNumber, fileName, fileExtension);
            	
            	// Printing message reply as log
            	System.out.println("Ukuran byte array: " + images.length);
    			System.out.println("Ukuran file: " + sendFile.length());
    			System.out.println(fileNumber + ". " + fileName + " berhasil dikirim\n");
    			
    			// Construct image like before
    			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageOut));
    			String pathNewFile = "";
    				
    			File destinationPath = new File(pathNewFile + fileNumber + "_" + fileName + "_grayscale." + fileExtension);
    				
    			ImageIO.write(image, fileExtension, destinationPath);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }      
    }

}

public class RMIClient {
	
    public static void main(String[] args) {
        //RMIClient main = new RMIClient();
        
        String[] worker = {"127.0.0.1", "127.0.0.1"};
        
        for (int i = 0; i < 2; i++) {
        	ThreadWorker t1 = new ThreadWorker(worker[i%2], i + 1);
        	t1.run();
        }
    }
    
}
