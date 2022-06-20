package vtp2022.day6.Server;

import java.io.IOException;
import java.net.Socket;

public class CookieClientHandler implements Runnable{
    
    private Socket sock;
    private String cookieFile;
    
    //read and write data to streams serves as an interceptor for NetworkIO class
    public CookieClientHandler(Socket s, String cookieFile){

        this.sock = s;
        this.cookieFile = cookieFile;
    }
    
    @Override
    public void run(){
        System.out.println("Starting a client thread");
        NetworkIO netIO = null;
        try {
            //NetworkIO is the class that will define all the input and outputstreams (refer to ServerApp from workshop 4)
            netIO = new NetworkIO(sock);
            String request = "";
            String randomCookieResp ="";

            //how does it become untrue?
            while (true) {
                //readUTF from client
                request=netIO.read();
                System.out.println("[client] " +request);
                if (request.trim().equals("exit"))
                    break;
                if (request.trim().equals("get-cookie")){
                    System.out.println("file -> "+ this.cookieFile);
                    randomCookieResp = Cookie.getRandomCookie(this.cookieFile);
                    //writeUTF to client
                    netIO.write("cookie-text "+randomCookieResp);
                    break;
                }else{
                    netIO.write("error, invalid command");
                    break;
                }
            }               
                
                netIO.close();
                sock.close();
                System.out.println("Exiting the thread!");
            
            
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
