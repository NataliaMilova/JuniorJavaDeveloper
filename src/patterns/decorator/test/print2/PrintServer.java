package patterns.decorator.test.print2;

import patterns.decorator.test.CryptFileInputStream;
import patterns.decorator.test.CryptFileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by xmitya on 28.08.16.
 */
public class PrintServer {

    private HashMap<String, String> users = new HashMap<>();
    private int port;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    public PrintServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            while (true) {

                Socket sock = ssocket.accept();
                sock.setTcpNoDelay(true);

                try {
                    process(sock);
                }
                catch (ClassNotFoundException e) {
                    System.err.println("Wrong message was received");

                    e.printStackTrace();
                }
                finally {
                    sock.close();
                }
            }
        }
    }

    private void process(Socket sock) throws IOException, ClassNotFoundException {
        String host = sock.getInetAddress().getHostAddress();

        try (ObjectInputStream objIn = new ObjectInputStream(new CryptFileInputStream(sock.getInputStream(), (byte)104));
             ObjectOutputStream objOut = new ObjectOutputStream(new CryptFileOutputStream(sock.getOutputStream(), (byte)104))) {

            Object obj = objIn.readObject();

            if (obj instanceof Message){
                printMessage((Message) obj, host);
                this.users.putIfAbsent(host, ((Message) obj).getSender());
            }
            else{
                Command command = new Command();
                command.readExternal(objIn);
                this.users.putIfAbsent(host, command.getSender());
                switch (command.getName()){
                    case "/ping":
                        pingCommand(objIn, objOut, sock);
                        break;
                    case "/list_users":
                        objOut.writeObject(this.users);
                        objOut.flush();
                        break;
                    case "/server_time":
                        objOut.writeLong(System.currentTimeMillis());
                        objOut.flush();
                        break;
                }
            }
        }
        catch (IOException | ClassNotFoundException | RuntimeException e) {
            System.err.println("Failed process connection from: " + host);

            e.printStackTrace();

            throw e;
        }
    }

    private void pingCommand(ObjectInputStream objIn, ObjectOutputStream objOut, Socket sock) throws IOException {
        int read;

        byte[] buf = new byte[1024];

        for (int i = 0; i < 1000; ++i){
            read = objIn.read();
            objOut.write(read);
            objOut.flush();
        }
    }

    private void printMessage(Message msg, String senderAddr) {
        System.out.printf("%s (%s) at %s wrote: %s\n", msg.getSender(), senderAddr, format.format(new Date(msg.getTimestamp())), msg.getText());
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");

        int port = Integer.parseInt(args[0]);

        PrintServer printServer = new PrintServer(port);

        printServer.start();
    }
}
