package com.dataman.base.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/** Appends text lines to a file (legacy DmHandelFile), e.g. to write a CSV export. UTF-8. */
public final class LineFileWriter implements AutoCloseable {

    private final BufferedWriter writer;

    /** Opens {@code file} for appending; creates it (and parent folders) when missing. */
    public LineFileWriter(Path file) throws IOException {
        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }
        this.writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public void writeLine(String content) throws IOException {
        writer.write(content);
        writer.write(System.lineSeparator());
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
