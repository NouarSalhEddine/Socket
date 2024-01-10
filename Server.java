    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SOCKET;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author kaa aziz
 */
public class Server implements Runnable {

    private static ServerSocket server;
    private static int port = 9999;

    public Server() throws IOException {
        server = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client Connection");

                Socket socket = server.accept();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject("What's your name?");
                
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                String message = (String) ois.readObject();
                System.out.println("Message Received: " + message);

                oos.writeObject("HEllo " + message);

                ois.close();
                oos.close();

                if (message.equalsIgnoreCase("exit")) {
                    socket.close();
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Error occured when trying to run the server");

            }
          

        }
        try {
            //close the ServerSocket object
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("UN PROBELEME LORS DE L'ARRET DU SERVEUR");
        }
    }

    public static void main(String args[]) throws IOException {
        new Thread(new Server()).start();
    }
}
