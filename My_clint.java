import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;

public class My_clint {
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    public My_clint(){
        try {
            s=new Socket("localhost",10);
            System.out.println(s);
            din =new DataInputStream(s.getInputStream());
            dout =new DataOutputStream(s.getOutputStream());
            clintChat();


        } catch (Exception e) {
           throw new RuntimeException();
        }
    }

    private void clintChat() throws IOException{
      String s1;
      BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
      do{
        s1 =br.readLine();
        dout.writeUTF(s1);
        dout.flush();
        String din = this.din.readUTF();
        System.out.println("Server massage: "+ din);
      }while(!s1.equals("Stop"));
    }

    public static void main(String[] args) {
        new My_clint();
    }
}
