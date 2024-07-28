package com.paulograbin.modernIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.DELETE_ON_CLOSE;

public class TemporaryFile {

    public static void main(String[] args) {
        Path p;
        try {
            p = Files.createTempFile("temp", null);
            System.out.println(p.getFileName());
            try (var output = Files.newOutputStream(p, DELETE_ON_CLOSE)) {
                // do work with the OutputStream
                // Temp file will be automatically cleaned up
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
