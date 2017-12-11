package streams2.browser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by evami on 20.11.17.
 */
public class Browser {
    public static void main(String[] args) throws IOException {
        int len;
        int count = 0;
        char[] buf = new char[1024];
        ServerSocket ssocket = new ServerSocket(12345);
        while (true) {
            Socket socket = ssocket.accept();
            ++count;
            try (Reader reader = new InputStreamReader(socket.getInputStream());
                 Writer writer = new OutputStreamWriter(socket.getOutputStream())) {

                while ((len = reader.read(buf)) > 0) {
                    for (int i = 0; i < len; ++i)
                        System.out.print(buf[i]);

                    writer.write("HTTP/1.1 200 OK\n\n");
                    writer.write("<html><body><h" + count + ">JULIAN!!</h" + count + "></body></html>");
                    writer.flush();
                    break;
                }
            }
        }
    }
}
