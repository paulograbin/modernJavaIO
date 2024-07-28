package com.paulograbin.modernIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import static java.nio.file.attribute.PosixFilePermission.GROUP_READ;

public class FileAttributes {

    public void manipulateFileAttributes() {
        try {
            var teamList = Path.of("/Users/ben/sample.txt");
            PosixFileAttributes attrs =
                    Files.readAttributes(teamList, PosixFileAttributes.class);

            Set<PosixFilePermission> posixFilePermissions = attrs.permissions();

            var owner = attrs.owner().getName();
            var perms = PosixFilePermissions.toString(posixFilePermissions);
            System.out.format("%s %s%n", owner, perms);

            posixFilePermissions.add(GROUP_READ);
            Files.setPosixFilePermissions(teamList, posixFilePermissions);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
