package com.paulograbin.modernIO;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Directories {

    private static String PATH = "/home/paulograbin/Desktop/experiments";


    public static void findMatchingFiles() {

        try {
            var dir = Path.of(PATH);
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.java");
            for (Path entry: stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findMatchingFilesRecursively() throws IOException {
        var startingDir = Path.of(PATH);
        Files.walkFileTree(startingDir, new FindJavaVisitor());
    }

    public class FindJavaVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file != null && attrs != null) {
                if (file.getFileName().toString().endsWith(".java")) {
                    System.out.println(file.getFileName());
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
//        Directories.findMatchingFiles();

        Directories d = new Directories();
        d.findMatchingFilesRecursively();
    }

}
