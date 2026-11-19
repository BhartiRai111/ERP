package com.dataman.base.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.enums.DmLocaleCountry;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

/**
 * Compares the new AmountInWords with the output of the LEGACY classes (DmAmtInWordLakh/Million),
 * generated once into golden/amount-in-words.txt: "country|base|amount|words"
 * (L = lakh format = India, M = million format = USA).
 */
class AmountInWordsGoldenTest {

    private static DmLocaleCountry of(String c) {
        return c.equals("L") ? DmLocaleCountry.INDIA : DmLocaleCountry.USA;
    }

    @Test
    void sameWordsAsLegacy() throws Exception {
        int checked = 0;
        try (InputStream in = getClass().getResourceAsStream("/golden/amount-in-words.txt");
             BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split("\\|", 4);
                String actual;
                try {
                    actual = AmountInWords.of(new BigDecimal(p[2]), of(p[0]), of(p[1]));
                } catch (Throwable t) {
                    actual = "EXC:" + t.getClass().getSimpleName();
                }
                assertEquals(p[3], actual, "country=" + p[0] + " base=" + p[1] + " amount=" + p[2]);
                checked++;
            }
        }
        assertTrue(checked > 1500, "golden file looks empty: " + checked);
    }

    @Test
    void spotChecks() {
        assertEquals("One Rupee & Five Paisa Only",
                AmountInWords.of(new BigDecimal("1.05"), DmLocaleCountry.INDIA, DmLocaleCountry.INDIA));
    }
}
