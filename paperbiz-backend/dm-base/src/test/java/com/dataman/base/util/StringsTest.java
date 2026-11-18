package com.dataman.base.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class StringsTest {

    @Test
    void foreignKeyBlankAndZeroAreNull() {
        assertNull(Strings.foreignKey(""));
        assertNull(Strings.foreignKey("  "));
        assertNull(Strings.foreignKey("0"));
        assertEquals("0", Strings.foreignKey("0", true));
        assertNull(Strings.foreignKey("", true));
        assertEquals("12", Strings.foreignKey("12"));
        assertNull(Strings.foreignKey(null));
    }

    @Test
    void removeSpaceCollapsesAndTrims() {
        assertEquals("a b c", Strings.removeSpace("  a   b  c  "));
        assertEquals("", Strings.removeSpace(null));
    }
}
