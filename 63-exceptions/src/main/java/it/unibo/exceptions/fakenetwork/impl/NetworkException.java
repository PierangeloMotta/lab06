package it.unibo.exceptions.fakenetwork.impl;

import java.io.IOException;

public class NetworkException extends IOException {
 
    public NetworkException(){
        System.out.println("[PIER] Network error: no response");
    }
    public NetworkException(String message){
        System.out.println("[PIER] Network error while sending message: <" + message + ">");
    }

}
