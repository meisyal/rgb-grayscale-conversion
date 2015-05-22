/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sister.kelompok5;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

	public byte[] Convert(byte[] imageByteArray, int fileNumber, String fileName, String fileExtension) throws IOException {
		// Handle byte array of image input
		BufferedImage imageIn = ImageIO.read(new ByteArrayInputStream(imageByteArray));
		
		// Get image attributes
		int height = imageIn.getHeight();
		int width = imageIn.getWidth();
		
		BufferedImage imageAttributes = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		// Convert to grayscale
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color color = new Color(imageIn.getRGB(x, y));
				
				int temp = (int) (color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);
				
				imageAttributes.setRGB(x, y, temp << 16 | temp << 8 | temp);
			}
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// Get byte array image again
		ImageIO.write(imageAttributes, fileExtension, baos);
		
		byte[] imageOut = baos.toByteArray();
		
		return imageOut;
	}
    
}
