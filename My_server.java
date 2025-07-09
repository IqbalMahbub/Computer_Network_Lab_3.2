import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.management.RuntimeErrorException;

public class My_server {
   
   ServerSocket ss;
   Socket s;
   DataInputStream dis;
   DataOutput dos;
   public My_server(){
    try{
        System.out.println("Server Started");
        ss= new ServerSocket(10);
        s =ss.accept();
        System.out.println("Clint connected");
        dis=new DataInputStream(s.getInputStream());
        dos= new DataOutputStream(s.getOutputStream());
        ServerChat();
    


    }catch(Exception e){
        throw new RuntimeException(e);
    }
   }

    private void ServerChat() throws IOException {
        String str,s1;
        do{
            str=dis.readUTF();
            System.out.println("Clint: "+ str);
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            s1=br.readLine();
            dos.writeUTF(s1);
            ((DataOutputStream) dos).flush();
        }while(!s1.equals("Bye"));

   }




    public static void main(String args[]){
        new My_server();
    }
}
