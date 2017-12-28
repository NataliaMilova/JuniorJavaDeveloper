package nio.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class NioClient {

    private BlockingDeque<String> messageQueue = new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        NioClient server = new NioClient();

        server.start();
    }

    public void start() {
        new Thread(new Sender()).start();
        new Thread(new NioClient.NioWorker()).start();
    }

    private class NioWorker extends Worker {
        private static final int BUF_SIZE = 1024;

        private SocketChannel clientCh;
        private Selector sel;
        private ByteBuffer buf;

        @Override
        protected void init() throws Exception {
            buf = ByteBuffer.allocate(BUF_SIZE);
            sel = Selector.open();

            clientCh = SocketChannel.open();
            clientCh.configureBlocking(false);
            clientCh.register(sel, SelectionKey.OP_CONNECT);
            clientCh.connect(new InetSocketAddress("localhost", 12345));
        }

        @Override
        protected void loop() throws Exception {
            sel.select(50);
            if (!messageQueue.isEmpty())
                clientCh.register(sel, SelectionKey.OP_WRITE);
            Set<SelectionKey> keys = sel.selectedKeys();
            Iterator<SelectionKey> iter = keys.iterator();

            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isConnectable()) {
                    System.out.println("Client connected to server");
                    connect(key);
                }
                else if (key.isReadable())
                    doRead(key);
                else if (key.isValid() && key.isWritable())
                    doWrite(key);
                iter.remove();
            }
        }

        @Override
        protected void stop() throws Exception {
            clientCh.close();
            sel.close();
        }

        private void doRead(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            buf.clear();
            int length = channel.read(buf);
            if (length == -1){
                System.out.println("Nothing was read from server");
                channel.close();
                key.cancel();
                return;
            }
            buf.flip();
            System.out.println(new String(buf.array(), 0, buf.limit(), Charset.forName("utf-8")));
        }

        private void doWrite(SelectionKey key) throws IOException, InterruptedException {
            SocketChannel channel = (SocketChannel) key.channel();
            channel.write(ByteBuffer.wrap(messageQueue.takeFirst().getBytes()));
            key.interestOps(SelectionKey.OP_READ);
        }

        private void connect(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            if (channel.isConnectionPending()){
                channel.finishConnect();
            }
            channel.configureBlocking(false);
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    private class Sender extends Worker {
        private Scanner scanner;

        @Override
        protected void loop(){
            String msg = scanner.nextLine();

            NioClient.this.messageQueue.add(msg);
        }

        @Override
        protected void init(){
            scanner = new Scanner(System.in);
        }
    }
}
