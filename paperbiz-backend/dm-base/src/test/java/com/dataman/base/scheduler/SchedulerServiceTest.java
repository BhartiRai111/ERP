package com.dataman.base.scheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.enums.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

class SchedulerServiceTest {

    private static Date d(int h, int mi) {
        return Date.from(LocalDateTime.of(2026, 7, 9, h, mi).atZone(ZoneId.systemDefault()).toInstant());
    }

    private SchedulePlan once(long id, boolean active, DmWebJobTypes type) {
        return new SchedulePlan(id, "j" + id, active, DmSchedularType.ONCE, null, 0, d(10, 0), null, false, null, null,
                null, null, null, null, type, 1, 1, 1, "1", "db", "fdb", null);
    }

    @Test
    void dueJobGoesToHandlerOfItsTypeAndFailuresAreIsolated() {
        List<Long> ran = new ArrayList<>();
        ScheduledJobHandler email = new ScheduledJobHandler() {
            public DmWebJobTypes jobType() { return DmWebJobTypes.EMAIL; }
            public void run(SchedulePlan p) { ran.add(p.docId()); }
        };
        ScheduledJobHandler sms = new ScheduledJobHandler() {
            public DmWebJobTypes jobType() { return DmWebJobTypes.SMS; }
            public void run(SchedulePlan p) { throw new IllegalStateException("boom"); }
        };
        SchedulerService s = new SchedulerService(List.of(email, sms), Runnable::run, true);
        s.tick(d(9, 59));
        s.schedule(once(1, true, DmWebJobTypes.EMAIL));
        s.schedule(once(2, true, DmWebJobTypes.SMS));
        s.schedule(once(3, false, DmWebJobTypes.EMAIL));
        assertEquals(2, s.list().size());
        assertEquals(2, s.tick(d(10, 1)).size());
        assertEquals(List.of(1L), ran);
    }

    @Test
    void stoppedWatchRunsNothing() {
        SchedulerService s = new SchedulerService(List.of(), Runnable::run, true);
        s.tick(d(9, 59));
        s.schedule(once(1, true, DmWebJobTypes.EMAIL));
        s.stopWatching();
        assertTrue(s.tick(d(10, 1)).isEmpty());
    }
}
