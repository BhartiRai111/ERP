package com.dataman.base.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.dataman.base.exception.DmException;
import org.junit.jupiter.api.Test;

class ValidateTest {

    @Test
    void mandatoryMessageAndErrorNumber() {
        DmException e = assertThrows(DmException.class, () -> Validate.mandatory(" ", " Unit Name ", 100));
        assertEquals("Unit Name is mandatory.", e.getMessage());
        assertEquals(100, e.getErrorNumber());
        assertDoesNotThrow(() -> Validate.mandatory("x", "Unit Name", 100));
    }

    @Test
    void amountMustBePositive() {
        assertThrows(DmException.class, () -> Validate.amount("0", "Value", 1));
        assertThrows(DmException.class, () -> Validate.amount("", "Value", 1));
        assertDoesNotThrow(() -> Validate.amount("1.5", "Value", 1));
    }

    @Test
    void emailAndPan() {
        assertDoesNotThrow(() -> Validate.email("", 1));
        assertDoesNotThrow(() -> Validate.email("a.b@c.com", 1));
        assertEquals("Invalid eMail Id.", assertThrows(DmException.class, () -> Validate.email("bad", 1)).getMessage());
        assertDoesNotThrow(() -> Validate.pan("ABCDE1234F", 1));
        assertEquals("Invalid PAN No.", assertThrows(DmException.class, () -> Validate.pan("abcde1234f", 1)).getMessage());
    }
}
