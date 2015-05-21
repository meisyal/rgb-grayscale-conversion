/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sister.kelompok5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Message extends UnicastRemoteObject implements MessageInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Message() throws RemoteException {        
    }
    
    public void Echo(String name) throws RemoteException {
        System.out.println("Echo from client: " + name);
    }
    
}
