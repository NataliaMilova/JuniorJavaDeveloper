package patterns.decorator.test.print2;

import patterns.decorator.test.CryptFileInputStream;
import patterns.decorator.test.CryptFileOutputStream;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by xmitya on 28.08.16.
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
            }
            else if ("/myaddr".equals(msg)) {
                printAddresses();

                continue;
            }
            else if ("/ping".equals(msg)) {
                buildAndSendPing(msg);

                continue;
            }
            else if ("/list_users".equals(msg)) {
                buildAndSendListUsers(msg);

                continue;
            }
            else if ("/server_time".equals(msg)) {
                buildAndSendServerTime(msg);

                continue;
            }
            else
                buildAndSendMessage(msg);
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

    private void buildAndSendPing(String msg) throws IOException {
        Command pingCommand = new Command(this.name, msg);
        System.out.println("Sent: " + msg);
        sendPing(pingCommand);
    }

    private void buildAndSendListUsers(String msg) throws IOException, ClassNotFoundException {
        Command listUsers = new Command(this.name, msg);
        System.out.println("Sent: " + msg);
        sendListUsers(listUsers);
    }

    private void buildAndSendServerTime(String msg) throws IOException, ClassNotFoundException {
        Command serverTime = new Command(this.name, msg);
        System.out.println("Sent: " + msg);
        sendServerTime(serverTime);
    }

    private void sendServerTime(Command serverTime) throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket()) {
            socket.setTcpNoDelay(true);
            socket.connect(serverAddr);
            try (ObjectOutputStream objOut = new ObjectOutputStream(new CryptFileOutputStream(socket.getOutputStream(), (byte)104));
                 ObjectInputStream objIn = new ObjectInputStream(new CryptFileInputStream(socket.getInputStream(), (byte)104))) {

                objOut.writeObject(serverTime.getName());
                serverTime.writeExternal(objOut);
                objOut.flush();

                System.out.println(new Date(objIn.readLong()));
            }
        }
    }

    private void sendListUsers(Command listUsers) throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket()) {
            socket.setTcpNoDelay(true);
            socket.connect(serverAddr);
            try (ObjectOutputStream objOut = new ObjectOutputStream(new CryptFileOutputStream(socket.getOutputStream(), (byte)104));
                 ObjectInputStream objIn = new ObjectInputStream(new CryptFileInputStream(socket.getInputStream(), (byte)104))) {

                objOut.writeObject(listUsers.getName());
                listUsers.writeExternal(objOut);
                objOut.flush();

                HashMap<String, String> users = (HashMap<String, String>) objIn.readObject();
                for (String i : users.keySet())
                    System.out.println(i + ": " + users.get(i));
            }
        }
    }

    private void sendPing(Command ping) throws IOException {
        long start, end;
        int read;
        try (Socket socket = new Socket()) {
//            socket.setSoTimeout(10);
            socket.setTcpNoDelay(true);
            socket.connect(serverAddr);
            try (ObjectOutputStream objOut = new ObjectOutputStream(new CryptFileOutputStream(socket.getOutputStream(), (byte)104));
                 ObjectInputStream objIn = new ObjectInputStream(new CryptFileInputStream(socket.getInputStream(), (byte)104))) {

                objOut.writeObject(ping.getName());
                ping.writeExternal(objOut);
                objOut.flush();

//                objOut.write(0);
//                objIn.read();

                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();

                byte[] buf = new byte[1024];

                for (int i = 0; i < 1000; ++i) {
                    start = System.currentTimeMillis();

                    objOut.write(i);
                    objOut.flush();
                    read = objIn.read();
                    end = System.currentTimeMillis();
                    System.out.println(end - start);
                }
            }
        }
    }

    private void buildAndSendMessage(String msg) throws IOException {
        Message message = new Message(System.currentTimeMillis(), name, msg);

        sendPrintMessage(message);

        System.out.println("Sent: " + message);
    }

    private void sendPrintMessage(Message msg) throws IOException {
        try (Socket socket = new Socket()) {
            socket.setTcpNoDelay(true);
            socket.connect(serverAddr);
            try (OutputStream out = socket.getOutputStream()) {
                ObjectOutputStream objOut = new ObjectOutputStream(new CryptFileOutputStream(out, (byte)104));
                objOut.writeObject(msg);

                objOut.flush();
            }
        }
    }

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
