package com.paulograbin.modernIO;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class WatchForChanges {

    private static String PATH = "/home/paulograbin/Desktop/experiments";

    private static volatile boolean shutdown = false;

    public static void main(String[] args) {
        try {
            var watcher = FileSystems.getDefault().newWatchService();
            var dir = Path.of(PATH);

            var registered = dir.register(watcher, ENTRY_MODIFY);

            while (!shutdown) {
                WatchKey key = null;
                try {
                    key = watcher.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event == null) {
                            continue;
                        }
                        if (event.kind() == ENTRY_MODIFY) {
                            System.out.println("Home dir changed!");
                        }
                    }
                    key.reset();
                } catch (InterruptedException e) {
                    // Log interruption
                    shutdown = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
