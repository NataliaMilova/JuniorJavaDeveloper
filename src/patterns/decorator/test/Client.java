package patterns.decorator.test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by evami on 22.11.17.
 */
public class Client {

    private static final File pass = new File("/home/evami/crypt/pass.txt");

    public static void main(String[] args) throws IOException, InterruptedException {
        try (Socket socket = new Socket()) {
            socket.setTcpNoDelay(true);
            socket.connect(new InetSocketAddress("localhost", 12345));
            try (ObjectOutputStream objOut = new ObjectOutputStream(new CryptFileOutputStream(socket.getOutputStream(),(byte)104));
                ObjectInputStream objIn = new ObjectInputStream(new CryptFileInputStream(socket.getInputStream(), (byte)104))) {

                objOut.write(1);

                objOut.flush();

                //int read = objIn.read();

                //System.out.println(read);
            }
        }
        }
    }
