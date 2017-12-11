package multithreading.fileserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by evami on 26.11.17.
 */
public class FileServer {
    private int port;
    private List<File> filesOnServer = new ArrayList<>();
    private File downloadsDir = new File("/home/evami/serverdownloads");


    public FileServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);
            for (File file: downloadsDir.listFiles()){
                filesOnServer.add(file);
            }
            System.out.println(filesOnServer);
            while (true) {
                Socket sock = ssocket.accept();

                new Thread(new Reader(sock)).start();
            }
        }
    }

    private class Reader implements Runnable {
        private final Socket socket;

        private Reader(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream objIn;
            int len;
            long size;
            byte[] buf = new byte[1024];
            try {

                objIn = new ObjectInputStream(socket.getInputStream());
                System.out.printf("%s connected\n", socket.getInetAddress().getHostAddress());

                while (!Thread.currentThread().isInterrupted()) {
                    FileDescriptor file = (FileDescriptor)objIn.readObject();
                    size = file.getSize();
                    try (OutputStream fileOut = new FileOutputStream(new File((FileServer.this.downloadsDir) + "/" + file.getName()))) {
                        while (size != 0) {
                            len = objIn.read(buf);
                            fileOut.write(buf, 0, len);
                            fileOut.flush();
                            size -= len;
                        }
                    }
                }
            }
            catch (IOException e) {
                System.err.println("Disconnected " + socket.getInetAddress().getHostAddress());
                IOUtils.closeQuietly(socket);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");

        int port = Integer.parseInt(args[0]);

        FileServer fileServer = new FileServer(port);

        fileServer.start();
    }
}
