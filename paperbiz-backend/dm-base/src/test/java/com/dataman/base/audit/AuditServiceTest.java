package com.dataman.base.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class AuditServiceTest {

    private static final Timestamp TS = Timestamp.valueOf(LocalDateTime.of(2026, 3, 5, 14, 7));

    @Test
    void formats() {
        assertEquals("05-Mar-2026 14.07", AuditService.formatTimestamp(TS));
        assertNull(AuditService.formatTimestamp(null));
        assertEquals("sam - 05-Mar-2026 - 14:07", AuditService.userWithDateTime("sam", TS));
        assertEquals("", AuditService.userWithDateTime("", TS));
        assertEquals("", AuditService.userWithDateTime("sam", null));
    }
}
