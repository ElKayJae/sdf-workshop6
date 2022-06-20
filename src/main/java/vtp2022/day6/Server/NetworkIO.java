package vtp2022.day6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkIO {
    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;

    //class to instantiate the datastreams and to define where to write to
    public NetworkIO(Socket sock) throws IOException {
        is = sock.getInputStream();
        dis = new DataInputStream(is);
        os = sock.getOutputStream();
        dos = new DataOutputStream(os);
    }

    public String read() throws IOException {
        //read from client
        return dis.readUTF();
    }

    public void write(String msg) throws IOException {
        //write to client
        dos.writeUTF(msg);
        dos.flush();
    }
    
    public void close(){
        try{
            dis.close();
            is.close();
            dos.close();
            os.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
