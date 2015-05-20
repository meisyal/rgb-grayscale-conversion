package com.sister.kelompok5;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

	private void sendMessage(){
        try {			
            /*
             * set to RMI server address and port
             * looking for service named "Echo", call remote method
             */
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 5000);					
            MessageInterface message = (MessageInterface) registry.lookup("Echo");						
            message.Echo("Hi server ...");			
            System.out.println("Message sent to server");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
    
    public static void main(String[] args) {
        RMIClient main = new RMIClient();
        main.sendMessage();
    }
    
}
