package com.jrsf.serverChannel;

import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.spi.*;
import java.util.*;
import java.net.*;

public class ServerChannelNB {

    private Selector selector;
    private ByteBuffer buffer;

    static public void main(String args[]) {
        new ServerChannelNB();
    }

    public ServerChannelNB(){
        try{
            int port = 4756;
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);

            selector = SelectorProvider.provider().openSelector();

            InetSocketAddress addressLocal = new InetSocketAddress(port);

            server.socket().bind(addressLocal);

            System.out.println("Server start on: " + port);
            System.out.println("addressLocal: " + addressLocal);

            server.register(selector, SelectionKey.OP_ACCEPT);
            while (true){
                selector.select();
                Set readyKeys = selector.selectedKeys();
                Iterator iterator = readyKeys.iterator();

                while (iterator.hasNext()){
                    SelectionKey selectionKey = (SelectionKey) iterator.next();
                    iterator.remove();

                    if (selectionKey.isAcceptable()){
                        startConnection(selectionKey);
                    }

                    if (selectionKey.isValid() && selectionKey.isReadable()){
                        reciveSendData(selectionKey);
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void startConnection(SelectionKey skey) throws Exception{
        ServerSocketChannel server = (ServerSocketChannel) skey.channel();
        SocketChannel connection = server.accept();
        connection.configureBlocking(false);
        connection.register(selector, SelectionKey.OP_READ);
    }

    public void reciveSendData(SelectionKey skey) throws Exception{
        SocketChannel connection = (SocketChannel) skey.channel();
        buffer = ByteBuffer.allocate(4);

        connection.read(buffer);
        buffer.flip();

        buffer.position(0);
        int received = buffer.getInt();

        String remote = connection.socket().getInetAddress().toString();
        remote = remote + ":" + connection.socket().getPort();
        System.out.println("Number received from the client: " + remote + "-" + received);

        int send = (int) Math.pow(received, 2);

        System.out.println("Number sent to the client: " + remote + "-" + send);

        buffer.clear();
        buffer.putInt(send);
        buffer.flip();
        connection.write(buffer);
    }
}
