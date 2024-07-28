package com.paulograbin.modernIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipInputStream;

public class ZipFiles {

    public static Path unpackJar(String zipFilePath) throws IOException {
        var tmpDir = Files.createTempDirectory(Path.of("/tmp"), "jar-extract");

        try (var zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            var entry = zipIn.getNextEntry();

            while (entry != null) {
                var newFile = tmpDir.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectory(newFile);
                } else {
                    Files.copy(zipIn, newFile);
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }

        return tmpDir;
    }

}
