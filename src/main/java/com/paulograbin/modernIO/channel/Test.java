package com.paulograbin.modernIO.channel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {

    public void checkSum() {
        FileInputStream fis = getSomeStream();
        boolean fileOK = true;

        try (FileChannel fchan = fis.getChannel()) {
            var buffy =
                    ByteBuffer.allocateDirect(16 * 1024 * 1024);

            while(fchan.read(buffy) != -1 || buffy.position() > 0 || fileOK) {
                fileOK = computeChecksum(buffy);
                buffy.compact();
            }
        }
        catch (IOException e) {
            System.out.println("Exception in I/O");
        }
    }

    private boolean computeChecksum(ByteBuffer buffy) {
        return false;
    }

    private FileInputStream getSomeStream() {
        return null;
    }

}
