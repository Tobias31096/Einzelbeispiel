package com.example.einzelbeispiel;

import java.io.*;
import java.net.*;

public class TCPClient extends Thread{
    private String userInput;
    private String modifiedSentence;

    public TCPClient(String userInput) {
        this.userInput = userInput;
    }

    public static void main(String[] args) {
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(userInput+'\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("Vom Server erhalten: " + modifiedSentence);
            clientSocket.close();
        } catch (Exception e){
            e.getStackTrace();
        }
    }
    public String returnModifiedSentence(){
        return this.modifiedSentence;
    }
}
