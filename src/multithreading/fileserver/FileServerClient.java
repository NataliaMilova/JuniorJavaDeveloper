package multithreading.fileserver;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Created by evami on 26.11.17.
 */
public class FileServerClient {
    private File uploadsDir;
    private SocketAddress serverAddr;
    private String name;
    private Scanner scanner;
    private Socket socket;
    private ObjectOutputStream objOut;

    public FileServerClient(SocketAddress serverAddr, Scanner scanner) {
        this.serverAddr = serverAddr;
        this.scanner = scanner;
        this.uploadsDir = new File("/home/evami/uploads");
    }

    public FileServerClient(File uploadsDir, SocketAddress serverAddr, Scanner scanner) {
        this.uploadsDir = uploadsDir;
        this.serverAddr = serverAddr;
        this.scanner = scanner;
    }

    private void start() throws IOException {
        openConnection();
        System.out.println("Enter your name: ");

        name = scanner.nextLine();
        new Thread(new Writer(socket)).start();
    }

    private static SocketAddress parseAddress(String addr) {
        String[] split = addr.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }


    private void openConnection() {
        try {
            socket = new Socket();

            socket.connect(serverAddr);

            objOut = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
            IOUtils.closeQuietly(socket);

            throw new FileServerUnchekedException("Error connecting to server", e);
        }
    }

    private static String[] parseMessage(String msg){
        return msg.trim().split("\\s");
    }

    private class Writer implements Runnable {
        private final Socket socket;

        public Writer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Enter command");
                String msg = scanner.nextLine();
                if ("/exit".equals(msg)) {
                    Thread.currentThread().interrupt();
                    IOUtils.closeQuietly(socket);
                    break;
                } else if ("/nick".equals(msg)) {
                    System.out.println("Enter new name:");
                    name = scanner.nextLine();
                    continue;
                } else if ("/upload".equals(msg)) {
                    System.out.println("Enter list of files: ");
                    String files = scanner.nextLine();
                    new Thread(new UploadFile(socket, files)).start();
                }
            }
        }
    }

    private class UploadFile implements Runnable{
        private String msg;
        private Socket socket;

        public UploadFile(Socket socket, String msg) {
            this.msg = msg;
            this.socket = socket;
        }

        @Override
        public void run() {
            int len;
            byte[] buf = new byte[1024];
            String[] files = parseMessage(msg);
            try {
                for (int i = 0; i < files.length; ++i) {
                    File file = new File(FileServerClient.this.uploadsDir.getPath() + "/" + files[i]);
                    if (!file.exists())
                        System.err.println(("File not found " + file.getName()));
                    else {
                        long size = file.length();
                        long size2 = size;
                        FileDescriptor fileDes = new FileDescriptor(files[i], size);
                        objOut.writeObject(fileDes);
                        objOut.flush();
                        try (InputStream fileIn = new FileInputStream(file)) {
                            while (size2 != 0) {
                                len = fileIn.read(buf);
                                objOut.write(buf, 0, len);
                                objOut.flush();
                                size2 -= len;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                IOUtils.closeQuietly(socket);
                throw new FileServerUnchekedException("Error sending upload " + e.getMessage(), e);
            }
        }
    }

    private class DownloadFile implements Runnable{

        @Override
        public void run() {
            try {
                objOut.writeUTF("/download");
                objOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        String addr = null;

        if (args != null && args.length > 0)
            addr = args[0];

        Scanner scanner = new Scanner(System.in);

        if (addr == null) {
            System.out.println("Enter server address");

            addr = scanner.nextLine();
        }

        FileServerClient client = new FileServerClient(parseAddress(addr), scanner);

        client.start();
    }

}
