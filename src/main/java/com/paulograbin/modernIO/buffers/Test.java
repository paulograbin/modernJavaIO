package com.paulograbin.modernIO.buffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Test {

    public static void main(String[] args) {
        ByteBuffer b = ByteBuffer.allocateDirect(128);
        ByteBuffer b2 = ByteBuffer.allocate(1024 * 1024);

        byte[] data = {1, 2, 3, 4, 5};
        ByteBuffer b3 = ByteBuffer.wrap(data);

        b.order(ByteOrder.LITTLE_ENDIAN);

        System.out.println("capacity " + b.capacity());
        System.out.println("position " + b.position());
        System.out.println("limit " + b.limit());
        System.out.println("remaining " + b.remaining());
        System.out.println("has remaining " + b.hasRemaining());

        b.put((byte)101);
        b.putChar('a');
        b.putInt(0xcafebabe);

        double d = b.getDouble();

        System.out.println(b.get());
        System.out.println(b.get());
        System.out.println(b.get());

        b.position(0);
        System.out.println(b.get());
        b.position(1);
        System.out.println(b.get());
        b.position(2);
        System.out.println(b.get());
    }

}
