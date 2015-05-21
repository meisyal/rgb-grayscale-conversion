/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sister.kelompok5;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageInterface extends Remote {
    //void Echo(String name) throws RemoteException;
	void Convert(byte[] imageByteArray, int fileNumber, String fileName, String fileExtension) throws RemoteException, IOException;
}
