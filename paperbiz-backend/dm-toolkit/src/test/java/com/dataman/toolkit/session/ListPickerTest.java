package com.dataman.toolkit.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.Test;

class ListPickerTest {

    @Test
    void requestedCodeOrTheOnlyEntryOrNone() {
        List<String> two = List.of("A", "B");
        assertEquals("B", ListPicker.pick(two, "b", s -> s));
        assertNull(ListPicker.pick(two, null, s -> s));
        assertNull(ListPicker.pick(two, "Z", s -> s));
        assertEquals("A", ListPicker.pick(List.of("A"), null, s -> s));
        assertEquals("A", ListPicker.pick(List.of("A"), "Z", s -> s), "single entry is chosen even if another was asked");
        assertNull(ListPicker.pick(List.<String>of(), "A", s -> s));
    }

    @Test
    void siteListIsPipeWrapped() {
        assertEquals(List.of("1", "12"), ListPicker.codes("|1|,|12|"));
        assertEquals(List.of(), ListPicker.codes(""));
        assertEquals(List.of(), ListPicker.codes(null));
    }
}
