import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class sendmail {

    private static DataOutputStream dos;
    public static BufferedReader br;

    public static void main(String[] args) throws Exception {
        String user = "s2111176129@ru.ac.bd";
        String pass = "yvubknralalflbvf";

        String username = Base64.getEncoder().encodeToString(user.getBytes());
        String password = Base64.getEncoder().encodeToString(pass.getBytes());

        SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
        dos = new DataOutputStream(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        readServerResponse(); 

        send("EHLO smtp.gmail.com\r\n");
        readServerResponse();

        send("AUTH LOGIN\r\n");
        readServerResponse();

        send(username + "\r\n");
        readServerResponse();

        send(password + "\r\n");
        readServerResponse();

        send("MAIL FROM:<" + user + ">\r\n");
        readServerResponse();

        send("RCPT TO:<iqbalmahbub420@gmail.com>\r\n");
        readServerResponse();

        send("DATA\r\n");
        readServerResponse();

        send("FROM: " + user + "\r\n");
        send("TO: iqbalmahbub@gmail.com\r\n");
        send("Subject: Test Email\r\n");
        send("\r\n");
        send("This is a test email sent from Java socket programming.\r\n");
        send(".\r\n");
        readServerResponse();

        send("QUIT\r\n");
        readServerResponse();

        dos.close();
        br.close();
        socket.close();
    }

    private static void send(String s) throws Exception {
        dos.writeBytes(s);
        System.out.println("Client: " + s.trim());
        Thread.sleep(500);
    }

    private static void readServerResponse() throws IOException {
        String line;
        do {
            line = br.readLine();
            System.out.println("SERVER: " + line);
        } while (line != null && (line.length() < 4 || line.charAt(3) == '-'));
    }
}
