package com.dataman.base.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ibm.icu.util.ULocale;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

/**
 * Compares NumberFormatter with the output of the LEGACY DmNumberFormat (ICU 4.8.1.1), generated once into
 * golden/number-format.txt: "country|places|uptoPlaces|symbol|comma|value|text".
 * Only known difference: 0.005 with 0 decimals (legacy "0.", new "0").
 */
class NumberFormatterGoldenTest {

    @Test
    void sameTextAsLegacy() throws Exception {
        int checked = 0;
        try (InputStream in = getClass().getResourceAsStream("/golden/number-format.txt");
             BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split("\\|", 7);
                BigDecimal value = p[5].equals("null") ? null : new BigDecimal(p[5]);
                String expected = p[6];
                if (p[1].equals("0") && "0.005".equals(p[5]) && expected.endsWith(".")) {
                    expected = expected.substring(0, expected.length() - 1);
                }
                String actual = NumberFormatter.format(value, Integer.parseInt(p[1]), Boolean.parseBoolean(p[2]),
                        Boolean.parseBoolean(p[3]), Boolean.parseBoolean(p[4]), new ULocale("en", p[0]));
                assertEquals(expected, actual, line);
                checked++;
            }
        }
        assertTrue(checked > 700, "golden file looks empty: " + checked);
    }

    @Test
    void indianGrouping() {
        assertEquals("12,34,567.89", NumberFormatter.format(new BigDecimal("1234567.891"), 2, true, false, true, new ULocale("en", "IND")));
        assertEquals("-", NumberFormatter.format(BigDecimal.ZERO, 2, true, false, true, new ULocale("en", "IND")));
    }
}
