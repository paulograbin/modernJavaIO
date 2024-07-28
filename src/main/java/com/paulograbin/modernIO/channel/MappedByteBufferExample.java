package com.paulograbin.modernIO.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferExample {

    public void aaa() throws IOException {
        try (var raf = new RandomAccessFile(new File("input.txt"), "rw");
             var fc = raf.getChannel()) {

            MappedByteBuffer mbf = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
            byte[] b = new byte[(int) fc.size()];

            mbf.get(b, 0, b.length);

            // Zero the in-memory copy
            for (int i = 0; i < fc.size(); i = i + 1) {
                b[i] = 0;
            }

            // Reposition to the start of the file
            mbf.position(0);

            // Zero the file
            mbf.put(b);
        }
    }

}
