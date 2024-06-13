package com.jrsf;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class ExampleByteBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(20);

        System.out.println("Properties of buffer was create \n");
        System.out.println("Capacity: " + buffer.capacity());
        System.out.println("Limit: " + buffer.limit());
        System.out.println("Position: " + buffer.position());

        System.out.println("Input value in buffer");
        buffer.put((byte) 10);
        buffer.put((byte) 28);
        buffer.put((byte) 57);

        System.out.println("Properties of buffer after input the values\n");
        System.out.println("Capacity: " + buffer.capacity());
        System.out.println("Limit: " + buffer.limit());
        System.out.println("Position: " + buffer.position());

        buffer.position(0);
        System.out.println("Read the values of buffer in the first form");

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get() + " ");
        }

        System.out.println();
        System.out.println("Read the values of buffer in the second form");

        byte[] array = buffer.array();
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }

        ByteBuffer buffer2 = ByteBuffer.wrap("I'm getting knowledge in Java NIO - ByteBuffer".getBytes());

        System.out.println("\n\nProperties of second ByteBuffer\n");
        System.out.println("Capacity of second ByteBuffer: " + buffer2.capacity());
        System.out.println("Limit of second ByteBuffer: " + buffer2.limit());
        System.out.println("Position of second ByteBuffer: " + buffer2.position());

        System.out.println("Read the values of second ByteBuffer - buffer2");

        while (buffer2.hasRemaining()) {
            System.out.println((char) buffer2.get() + " ");
        }

        buffer.position(10);
        buffer.putInt(11);
        buffer.putInt(65);
        System.out.println("\n\n int on position 10: " + buffer.getInt(10));
        System.out.println(" int on position 14: "+ buffer.getInt(14));

        System.out.println();
        buffer.position(0);

        System.out.println("Final content of ByteBuffer");
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

        System.out.println();
        System.out.println();

        buffer = ByteBuffer.allocate(100);
        CharBuffer textBuffer = buffer.asCharBuffer();
        textBuffer.put("Hello, I'm learning Java NIO - ByteBuffer");

        textBuffer.position(0);
        System.out.print("String in buffer: ");
        while (textBuffer.hasRemaining()) {
            System.out.print(textBuffer.get());
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
