package com.dataman.base.serial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SerialNoServiceTest {

    @Test
    void integerKeyIsOneSiteCounter() {
        assertEquals("1000100" + "07", SerialNoService.format("1", 7, "0000").substring(0, 9));
        assertEquals("100010007", SerialNoService.format("1", 7, "0000"));
        assertEquals("100000042", SerialNoService.format("", 42, "0000"));
    }

    @Test
    void bigIntKeyHas14DigitCounter() {
        assertEquals("1" + "0002" + "00000000000123", SerialNoService.format("2", 123, "00000000000000"));
    }
}
