package vtp2022.day6.Client;


import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// run mvn compile exec:java -Dexec.mainClass="vtp2022.day6.Client.ClientApp" -Dexec.args="0.0.0.0:3000"
public class ClientApp {
  public static void main(String[] args) {
  String arg = args[0];
  String[] arr = arg.split(":");
  String host = arr[0];
  Integer port = Integer.parseInt(arr[1]) ;
  InputStream is =null;
  DataInputStream dis = null;
  Socket socket = null;
  OutputStream os =null;
  DataOutputStream dos =null;
  boolean stop = false;
  

  try {
    Console cons = System.console();
    while (!stop) {
      String response = null;

      //read command from terminal
      String input = cons.readLine("Send command to server > ");
      
      //initiate connection
      socket = new Socket(host,port);

      //define inputstream
      is = socket.getInputStream();
      dis = new DataInputStream(is);
      
      //define outputstream
      os = socket.getOutputStream();
      dos = new DataOutputStream(os);

      //check input from terminal
      if (input.equals("exit")) 
        stop=true;
      dos.writeUTF(input);
      dos.flush();

      //read response from server, if condition is true ie. !stop = true, execute the block
      if (!stop) {
        try {
          //response = readUTF of DataInputStream
          response =dis.readUTF();
        } catch (EOFException e) {
          // suppress if the reading is called twice
        }

        // if response is not null
        if (response != null) {
          //check if response contains either
          if(response.contains("cookie-text") || response.contains("error,")){
            System.out.println(response);
            String[] cookieValue = response.split(" ", 2);
            if(response.contains("error,")){
              System.out.println(("Error from server >> "+ cookieValue[1]));
            }
            if(response.contains("cookie-text")){
            System.out.println("Cookie from server >> " + cookieValue[1]);
            }
          }
        }
      }
    }
      System.out.println("closing...");
      is.close();
      os.close();
      socket.close();




  } catch (NumberFormatException e){
    e.printStackTrace();
  } catch (IOException e){
    e.printStackTrace();
  }
    
    
}
}
