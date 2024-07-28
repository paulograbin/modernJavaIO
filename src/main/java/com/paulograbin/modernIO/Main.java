package com.paulograbin.modernIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {

        var logFile = Path.of("/home/paulograbin/my.log");
        try (var writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
            writer.write("Hello World!");
// ...
        } catch (IOException e) {
            System.err.println(" Erro " + e.getMessage());
        }
    }


    public void copyExample() throws IOException {
        var input = Path.of("input.txt");
        var output = Path.of("input.txt");

        try {
            Files.copy(input, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Files.delete(input);

        long size = Files.size(input);


        Files.copy(input, output, StandardCopyOption.REPLACE_EXISTING);
    }

    public int countTimesLowerCaseA() {
        try (var is = new FileInputStream("aaa/alice.txt")) {

            var buf = new byte[4096];
            int len = 0;
            int count = 0;

            while ((len = is.read(buf)) > 0) {
                for (var i = 0; i < len; i++) {
                    if (buf[i] == 97) {
                        count++;
                    }
                }
            }

            return count;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long countTimesUpperCaseA__newAPI() {
        var alice = Path.of("alice.txt");

        try {

            return Files.lines(alice)
                    .flatMap(l -> Stream.of(l.split("")))
                    .filter(s -> s.equals("a"))
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}