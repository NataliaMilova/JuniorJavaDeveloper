package streams2.print1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by evami on 16.11.17.
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

        try (ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream())) {
            String text = objIn.readUTF();
            if (text.equals("message")) {
                Object obj = objIn.readObject();
                if (obj instanceof Message) {
                    users.put(host, ((Message) obj).getSender());
                    printMessage((Message) obj, host);
                    return;
                }
            }
            else {
                SystemCommand command = new SystemCommand();
                command.readExternal(objIn);
                switch (command.getName()) {
                    case "/ping":
                        pingCommand(objIn, out);
                        break;
                    case "/list_users":
                        out.writeInt(command.getNumber());
                        out.writeObject(this.users);
                        out.flush();
                        System.out.println("Send list_users to " + host);
                        break;
                    case "/server_time":
                        out.writeInt(command.getNumber());
                        out.writeLong(System.currentTimeMillis());
                        out.flush();
                        System.out.println("Send server time to " + host);
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

    private static void pingCommand(ObjectInputStream in, ObjectOutputStream out) throws IOException {
        int read;
        for (int i = 0; i < 100; ++i){
            read = in.read();
            out.write(read);
            out.flush();
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
