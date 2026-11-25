package com.dataman.base.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class FieldUdtTest {

    @Test
    void skipsBlankKeysAndKeepsOrder() throws Exception {
        Map<String, FieldUdt> m = new LinkedHashMap<>();
        m.put("itemName", new FieldUdt("Item", "Paper", "x"));
        m.put(" ", new FieldUdt("b", "b", "b"));
        m.put(null, new FieldUdt("n", "n", "n"));
        var it = FieldUdt.toTable(m).getIterator();
        int rows = 0;
        while (it.hasNext()) { it.next(); rows++; }
        assertEquals(1, rows);
    }

    @Test
    void rejectsTooLongCodeOrName() {
        Map<String, FieldUdt> longKey = Map.of("k".repeat(31), new FieldUdt("a", "b", "c"));
        Map<String, FieldUdt> longName = Map.of("k", new FieldUdt("n".repeat(31), "b", "c"));
        assertThrows(IllegalArgumentException.class, () -> FieldUdt.toTable(longKey));
        assertThrows(IllegalArgumentException.class, () -> FieldUdt.toTable(longName));
    }
}
