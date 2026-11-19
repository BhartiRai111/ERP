package com.dataman.base.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DateUtilTest {

    @Test
    void displayAndParse() {
        assertEquals("05-Mar-2026", DateUtil.display("05-03-2026"));
        assertEquals("05-Mar-2026", DateUtil.display("05/03/2026"));
        assertEquals("05-Mar-2026", DateUtil.display("05.03.2026"));
        assertEquals("", DateUtil.display("garbage"));
        assertEquals("", DateUtil.display((String) null));
        assertNull(DateUtil.parse(""));
    }
}
