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

        int capacity = b.capacity();
        int position = b.position();
        int limit = b.limit();
        int remaining = b.remaining();
        boolean more = b.hasRemaining();

        b.put((byte)101);
        b.putChar('a');
        b.putInt(0xcafebabe);

        double d = b.getDouble();
    }

}
