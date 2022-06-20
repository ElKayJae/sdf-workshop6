package vtp2022.day6.Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {

    // run mvn compile exec:java -Dexec.mainClass="vtp2022.day4.Server.ServerApp" -Dexec.args="3000 C:\Users\lowke\Desktop\NUSISS\sdf-workshop4\src\main\java\vtp2022\day4\Server\cookie_file.txt"    
    public static void main(String[] args) {
        System.out.println("Server App");
        int port = 3001;
        if (args.length>0)
            port = Integer.parseInt(args[0]);

            String cookieFile = args[1];
            ExecutorService threadpool = Executors.newFixedThreadPool(2);
            ServerSocket server =  new ServerSocket();

            while (true) {
                System.out.println("Waiting for client connection");
                Socket sock = server.accept();
                System.out.println("Connected");
                CookieClientHandler thr = new CookieClientHandler(sock,cookieFile);
                
            }
    }
    
   
}