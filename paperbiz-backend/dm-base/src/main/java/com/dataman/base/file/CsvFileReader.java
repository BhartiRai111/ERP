package com.dataman.base.file;

import com.dataman.base.exception.DmException;
import com.dataman.base.util.Strings;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads a CSV file the way the legacy DmCsvReader did: first line = headers (spaces collapsed, trimmed),
 * every other line = a row keyed by the lower-case header. Separator ',', quote '"', escape '|'
 * (or strict quotes with the normal escape).
 */
public final class CsvFileReader {

    public interface RowHandler {

        /** Called once for the header line; return false to stop. */
        boolean onHeaders(List<String> headers);

        /** Called for each data line (lineNo 1 = first data line); return false to stop. */
        boolean onRow(int lineNo, List<String> headers, Map<String, String> row);
    }

    public record CsvContent(List<String> headers, List<Map<String, String>> rows) {
    }

    private CsvFileReader() {
    }

    public static CsvContent readAll(Path file, boolean strictQuotes) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        read(file, strictQuotes, new RowHandler() {
            @Override
            public boolean onHeaders(List<String> h) {
                headers.addAll(h);
                return true;
            }

            @Override
            public boolean onRow(int lineNo, List<String> h, Map<String, String> row) {
                rows.add(row);
                return true;
            }
        });
        return new CsvContent(headers, rows);
    }

    public static void read(Path file, boolean strictQuotes, RowHandler handler) throws IOException {
        if (!Files.exists(file)) {
            throw new DmException("File not exists.");
        }
        CSVParserBuilder parser = new CSVParserBuilder().withSeparator(',').withQuoteChar('"');
        if (strictQuotes) {
            parser.withStrictQuotes(true);
        } else {
            parser.withEscapeChar('|');
        }
        try (Reader in = Files.newBufferedReader(file, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReaderBuilder(in).withCSVParser(parser.build()).build()) {
            List<String> headers = new ArrayList<>();
            String[] line;
            int lineNo = 0;
            boolean first = true;
            while ((line = reader.readNext()) != null) {
                if (first) {
                    for (String cell : line) {
                        headers.add(Strings.removeSpace(Strings.xNull(cell)).trim());
                    }
                    if (!handler.onHeaders(headers)) {
                        return;
                    }
                } else {
                    if (line.length > headers.size()) {
                        throw new DmException("Line " + lineNo + " has more values than headers.");
                    }
                    Map<String, String> row = new LinkedHashMap<>();
                    for (int i = 0; i < line.length; i++) {
                        row.put(headers.get(i).trim().toLowerCase(), Strings.xNull(line[i]));
                    }
                    if (!handler.onRow(lineNo, headers, row)) {
                        return;
                    }
                }
                lineNo++;
                first = false;
            }
        } catch (CsvValidationException e) {
            throw new DmException(e.getMessage());
        }
    }
}
