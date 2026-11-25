package com.dataman.base.serial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RecIdFormatTest {

    private final RecIdFormat.Inputs in = new RecIdFormat.Inputs("2", "HO", "PB", "21", "5", "PO",
            LocalDate.of(2026, 7, 9), LocalDate.of(2026, 4, 1), LocalDate.of(2027, 3, 31));

    @Test
    void expandsTokensAndCounter() {
        assertEquals("PO/26/0007",
                RecIdFormat.value("{vouchersname}/{year}/{counter}", in, null, "0000", 7));
        assertEquals("PO/26/", RecIdFormat.prefix("{vouchersname}/{year}/{counter}", in, null));
    }

    @Test
    void paddingAndFinancialYearTokens() {
        assertEquals("002-05-021-2026-0709-2026-2027|26-27",
                RecIdFormat.prefix("{site}-{voucherprefix}-{voucher}-{yearfull}-{month}{day}-{fullstartyear}-{fullendyear}|{startyear}-{endyear}",
                        in, null));
    }

    @Test
    void extraTokensAreTrimmed() {
        assertEquals("X-AB-1", RecIdFormat.value("X-{grp}-{counter}", in, Map.of("grp", " AB "), "0", 1));
    }
}
