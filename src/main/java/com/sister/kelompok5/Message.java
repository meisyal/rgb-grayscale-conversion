/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sister.kelompok5;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.imageio.ImageIO;

public class Message extends UnicastRemoteObject implements MessageInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Message() throws RemoteException {        
    }

	public void Convert(byte[] imageByteArray, int fileNumber, String fileName, String fileExtension) throws IOException {
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageByteArray));
				
		String pathNewFile = "";
			
		File path = new File(pathNewFile + fileNumber + "_" + fileName + "_grayscale." + fileExtension);
			
		ImageIO.write(image, fileExtension, path);
	}
    
}
