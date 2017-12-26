package nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NioClient {

    public static void main(String[] args) {
        NioClient server = new NioClient();

        server.start();
    }

    public void start() {

        new Thread(new NioClient.NioWorker()).start();
    }

    private class NioWorker extends Worker {
        private static final int BUF_SIZE = 1024;

        private SocketChannel clientCh;
        private Selector sel;
        private ByteBuffer buf;
        private Scanner scanner = new Scanner(System.in);

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
            sel.select();
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
            key.interestOps(SelectionKey.OP_WRITE);
        }

        private void doWrite(SelectionKey key) throws IOException {
            String msg = scanner.nextLine();
            msg += "\n";
            SocketChannel channel = (SocketChannel) key.channel();
            channel.write(ByteBuffer.wrap(msg.getBytes()));

            key.interestOps(SelectionKey.OP_READ);
        }

        private void connect(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            if (channel.isConnectionPending()){
                channel.finishConnect();
            }
            channel.configureBlocking(false);
            channel.register(sel, SelectionKey.OP_WRITE);
        }
    }
}
