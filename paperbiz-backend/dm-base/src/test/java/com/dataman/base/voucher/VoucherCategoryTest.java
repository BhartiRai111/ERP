package com.dataman.base.voucher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class VoucherCategoryTest {

    @Test
    void categoryBelongsToItsGroup() {
        assertEquals("GENSI", VoucherCategory.getGroupViaCategory(VoucherCategory.GENERAL_SALES_INVOICE));
        assertEquals("GSI", VoucherCategory.GENERAL_SALES_INVOICE);
        assertEquals("", VoucherCategory.getGroupViaCategory("NOPE"));
    }

    @Test
    void csvHelpers() {
        assertEquals("GSI", VoucherCategory.getCategoryCSV("GENSI"));
        assertEquals("'GSI'", VoucherCategory.getCategoryCSV_SQL("GENSI"));
        assertTrue(VoucherCategory.getCategoryList("UNKNOWN").isEmpty());
    }

    @Test
    void unregisteredFlag() {
        assertTrue(VoucherCategory.isUnRegistered(VoucherCategory.GENERAL_QUOTATION_UNREGISTERED));
        assertFalse(VoucherCategory.isUnRegistered(VoucherCategory.GENERAL_SALES_INVOICE));
    }

    @Test
    void groupLookups() {
        assertEquals(VoucherGroup.GEN_SALES_INVOICE, VoucherGroup.getViaManualCode("gensi"));
        assertEquals(VoucherGroup.GEN_SALES_INVOICE, VoucherGroup.getViaValue("sales invoice"));
        assertNull(VoucherGroup.getViaManualCode("zzz"));
    }
}
