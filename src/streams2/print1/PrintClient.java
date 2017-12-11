package streams2.print1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * Created by evami on 16.11.17.
 */
public class PrintClient {

    private SocketAddress serverAddr;

    private String name;

    private Scanner scanner;

    public PrintClient(SocketAddress serverAddr, Scanner scanner) {
        this.serverAddr = serverAddr;
        this.scanner = scanner;
    }

    private void start() throws IOException, ClassNotFoundException {
        System.out.println("Enter your name: ");

        name = scanner.nextLine();

        while (true) {
            System.out.println("Enter message to send: ");

            String msg = scanner.nextLine();

            if ("/exit".equals(msg))
                break;
            else if ("/nick".equals(msg)) {
                System.out.println("Enter new name:");

                name = scanner.nextLine();

                continue;
            } else if ("/myaddr".equals(msg)) {
                printAddresses();

                continue;
            }
            else
                try (Socket sock = new Socket()) {
                    sock.connect(serverAddr);

                    try (ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream());
                         ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream())) {

                        pingCommand(objIn, objOut);


                        /*if ("/list_users".equals(msg)) {
                            buildSystemCommand(msg);

                            continue;
                        } else if ("/server_time".equals(msg)) {
                            buildSystemCommand(msg);

                            continue;
                        } else if ("/ping".equals(msg)) {
                            buildSystemCommand(msg);

                            continue;
                        }
                        buildAndSendMessage(msg);*/
                    }
                }
        }
    }

    private void printAddresses() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while(e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();

            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();

                System.out.println(i.getHostAddress());
            }
        }
    }

    /*private void buildAndSendMessage(String msg) throws IOException, ClassNotFoundException {
        Message message = new Message(System.currentTimeMillis(), name, msg);

        sendPrintMessage(message);

        System.out.println("Sent: " + message);
    }

    private void buildSystemCommand(String msg) throws IOException, ClassNotFoundException {
        SystemCommand list = new SystemCommand(1, msg, System.currentTimeMillis());
        System.out.println("Sent: " + list.getName());
        sendPrintMessage(list);
    }
*/
    private static void pingCommand(ObjectInputStream in, ObjectOutputStream out) throws IOException {
        long start, end;
        int read;
        SystemCommand list = new SystemCommand(1, "/ping", System.currentTimeMillis());
        out.writeUTF("command");
        list.writeExternal(out);
        out.flush();
        for (int i = 0; i< 100; ++i){
            start = System.currentTimeMillis();
            out.writeByte(1);
            out.flush();
            read = in.read();
            end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }

    /*private void sendPrintMessage(Object msg) throws IOException, ClassNotFoundException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

                if (msg instanceof SystemCommand) {
                    SystemCommand command = (SystemCommand) msg;
                    objOut.writeUTF("command");
                    command.writeExternal(objOut);
                    if (command.getName().equals("/ping")){
                        pingCommand(objIn, objOut);
                        return;
                    }
                    objOut.flush();
                    if (objIn.readInt() == command.getNumber()){
                        switch (command.getName()){
                            case "/list_users":
                                HashMap<String, String> result = (HashMap<String, String>) objIn.readObject();
                                System.out.println("Get from server list users");
                                for (String i: result.keySet())
                                    System.out.println(i + ": " + result.get(i));
                                break;
                            case "/server_time":
                                System.out.println(new Date(objIn.readLong()));
                                break;
                        }

                    }
                }
                else {
                    objOut.writeUTF("message");
                    objOut.writeObject(msg);
                    objOut.flush();
                }
            }
        }
    }*/

    private static SocketAddress parseAddress(String addr) {
        String[] split = addr.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String addr = null;

        if (args != null && args.length > 0)
            addr = args[0];

        Scanner scanner = new Scanner(System.in);

        if (addr == null) {
            System.out.println("Enter server address");

            addr = scanner.nextLine();
        }

        PrintClient client = new PrintClient(parseAddress(addr), scanner);
        client.start();
    }
}

