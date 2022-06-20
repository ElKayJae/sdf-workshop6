package vtp2022.day6.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {

    // run mvn compile exec:java -Dexec.mainClass="vtp2022.day6.Server.ServerApp" -Dexec.args="3000 C:\Users\lowke\Desktop\NUSISS\sdf-workshop6\src\main\java\vtp2022\day6\Server\cookie_file.txt"    
    public static void main(String[] args) throws IOException {
        System.out.println("Server App");
        int port = 3001;
        if (args.length>0)
            port = Integer.parseInt(args[0]);

            String cookieFile = args[1];
            //instantiate 2 threads
            ExecutorService threadPool = Executors.newFixedThreadPool(2);
            ServerSocket server =  new ServerSocket(port);

            while (true) {
                System.out.println("Waiting for client connection");
                Socket sock = server.accept();
                System.out.println("Connected");
                CookieClientHandler thr = new CookieClientHandler(sock,cookieFile);
                threadPool.submit(thr);
                System.out.println("Submitted to threadpool");
            }
    }
    
   
}