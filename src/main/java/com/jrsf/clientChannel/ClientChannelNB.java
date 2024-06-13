package com.jrsf.clientChannel;

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import javax.swing.JOptionPane;

public class ClientChannelNB {

    static public void main(String args[]) {
        try{
            int port = 4756;
            String serverAddress = JOptionPane.showInputDialog(null,"Enter the server address: ");
            System.out.println("Client start on: " + serverAddress + ":" + port);
            SocketChannel connection = SocketChannel.open();

            InetSocketAddress addressRemote = new InetSocketAddress(serverAddress, port);

            connection.connect(addressRemote);

            ByteBuffer buffer = ByteBuffer.allocate(4);

            while (true){
                int num = (int) (Math.random() * 100);
                buffer.putInt(num);
                buffer.flip();

                connection.write(buffer);
                System.out.println("Number sent to server: " + num);

                buffer.clear();
                connection.read(buffer);

                buffer.position(0);
                int received = buffer.getInt();

                System.out.println("Number received from server: " + received);

                buffer.clear();
                Thread.sleep((int) (Math.random() * 5000) + 300);
            }
        } catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
