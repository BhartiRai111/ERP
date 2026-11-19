package com.dataman.base.file;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.stream.Stream;

/** Zips a folder (all files, relative paths inside the zip) - legacy ZipUtils. */
public final class ZipFolder {

    private ZipFolder() {
    }

    public static void zip(Path sourceFolder, Path outputZip) throws IOException {
        try (OutputStream out = Files.newOutputStream(outputZip);
             ZipOutputStream zos = new ZipOutputStream(out);
             Stream<Path> files = Files.walk(sourceFolder)) {
            for (Path file : (Iterable<Path>) files.filter(Files::isRegularFile)::iterator) {
                String entryName = sourceFolder.relativize(file).toString().replace('\\', '/');
                zos.putNextEntry(new ZipEntry(entryName));
                Files.copy(file, zos);
                zos.closeEntry();
            }
        }
    }
}
