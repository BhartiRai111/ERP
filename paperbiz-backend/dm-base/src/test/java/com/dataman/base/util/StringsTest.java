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

    @Test
    void numbersLikeLegacy() {
        assertEquals(java.math.BigInteger.ZERO, Strings.iNull(null));
        assertEquals(java.math.BigInteger.ZERO, Strings.iNull(""));
        assertEquals(java.math.BigInteger.valueOf(12), Strings.iNull("12.7"));
        assertEquals(java.math.BigInteger.ZERO, Strings.iNull(".5"));
        assertEquals(0.0, Strings.vNull(""));
        assertEquals(2.5, Strings.vNull("2.5"));
        assertEquals(true, Strings.isNumeric("1.5"));
        assertEquals(false, Strings.isNumeric("x"));
    }

    @Test
    void cases() {
        assertEquals("Hello World", Strings.properCase("  hELLO   wORLD "));
        assertEquals("Hi there. How are you? Fine!", Strings.sentenceCase("hI THERE. hOW ARE YOU? fINE!"));
        assertEquals("   ", Strings.space(3));
        assertEquals("", Strings.space(0));
    }
}
