/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP03_SOCKET;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaa aziz
 */
public class Client implements Runnable {

    private static String serverAdress = "127.0.0.1";
    // socket server port on which it will listen
    private static int port = 9999;
    private Socket socket = null;

    public Client() {

    }

    public void run() {
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            // establish socket connection to server
            socket = new Socket(serverAdress, port);
            // write to socket using ObjectOutputStream
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            oos = new ObjectOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            oos.writeObject(sc.nextLine());
            // read the server response message
            message = (String) ois.readObject();
            System.out.println("Message: " + message);
            // close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        new Thread(new Client()).start();
    }
}
