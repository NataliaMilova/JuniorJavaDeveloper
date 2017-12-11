package patterns.decorator.test;

import patterns.decorator.*;
import patterns.decorator.test.*;
import patterns.decorator.test.CryptFileInputStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by evami on 22.11.17.
 */
public class Server {

    private static final File pass = new File("/home/evami/crypt/pass.txt");

    public static void main(String[] args) throws IOException {
        try (ServerSocket ssocket = new ServerSocket(12345)) {
            System.out.println("Server started on " + ssocket);

            Socket sock = ssocket.accept();
            sock.setTcpNoDelay(true);
            try (ObjectInputStream objIn = new ObjectInputStream(new CryptFileInputStream(sock.getInputStream(), (byte)104));
                ObjectOutputStream objOut = new ObjectOutputStream(new CryptFileOutputStream(sock.getOutputStream(), (byte)104))) {

                int read = objIn.read();
                System.out.println(read);

                //objOut.write(2);
                //objOut.flush();
            }
        }
    }
}
