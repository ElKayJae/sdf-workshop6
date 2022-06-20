package vtp2022.day6.Server;

import java.io.IOException;
import java.net.Socket;

public class CookieClientHandler implements Runnable{
    private Socket sock;
    private String cookieFile;

    public CookieClientHandler(Socket s, String cookieFile){
        this.sock = s;
        this.cookieFile = cookieFile;
    }
    
    @Override
    public void run(){
        System.out.println("Starting a client thread");
        try {
            NetworkIO netIO = new NetworkIO(sock);
            String req = "";
            while (!req.equals("exit")) {
                req=netIO.read();
                System.out.println("[client] " +req);
                if (req.trim().equals("exit")) {
                    break;
                    //TODO implement the random cookie and return the string to client
                    netIO.write("");
                }
                
                netIO.close();
                sock.close();
                System.out.println("Exiting the thread!");
            }
            
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
