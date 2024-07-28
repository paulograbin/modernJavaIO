package com.paulograbin.modernIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncIO {

    private String PATH = "/Users/paulograbin/Desktop/measurements.txt";

    public static void main(String args[]) {
        AsyncIO asyncIO = new AsyncIO();
        asyncIO.futureStyle();

        asyncIO.callbackStyle();
    }

    public void callbackStyle() {
        var file = Path.of(PATH);

        try (var channel = AsynchronousFileChannel.open(file)) {
            var buffer = ByteBuffer.allocate(100);


            var handler = new CompletionHandler<Integer, ByteBuffer>() {
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("Bytes read [" + result + "]");
                }

                public void failed(Throwable exception, ByteBuffer attachment) {
                    exception.printStackTrace();
                }
            };

            channel.read(buffer, 0, buffer, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void futureStyle() {
        var file = Path.of(PATH);

        try (var channel = AsynchronousFileChannel.open(file)) {
            var buffer = ByteBuffer.allocate(100);
            Future<Integer> result = channel.read(buffer, 0);

            doSomethingElse();

//            result.isDone()

            var bytesRead = result.get();
            System.out.println("Bytes read [" + bytesRead + "]");
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doSomethingElse() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
