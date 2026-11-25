package com.dataman.base.scheduler;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.enums.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

class ScheduleEvaluatorTest {

    private static Date d(int y, int mo, int day, int h, int mi) {
        return Date.from(LocalDateTime.of(y, mo, day, h, mi).atZone(ZoneId.systemDefault()).toInstant());
    }

    private static SchedulePlan plan(DmSchedularType t, DmRecurrenceUnit u, Date start, Date last,
            List<DmWeekdays> days, List<DmMonths> months, List<DmDates> dates, List<DmWeeks> weeks, String dm) {
        return new SchedulePlan(1, "t", true, t, u, 1, start, null, false, last, dm, days, months, dates, weeks,
                DmWebJobTypes.EMAIL, 1, 1, 1, "1", "db", "fdb", List.of(1));
    }

    @Test
    void onceFiresInsideWindowOnlyOnce() {
        SchedulePlan p = plan(DmSchedularType.ONCE, null, d(2026, 7, 9, 10, 0), null, null, null, null, null, null);
        assertTrue(ScheduleEvaluator.shouldExecute(p, d(2026, 7, 9, 9, 59), d(2026, 7, 9, 10, 1)));
        assertFalse(ScheduleEvaluator.shouldExecute(p, d(2026, 7, 9, 10, 1), d(2026, 7, 9, 10, 2)));
        assertFalse(ScheduleEvaluator.shouldExecute(p.withLastExecution(d(2026, 7, 9, 10, 0)),
                d(2026, 7, 9, 9, 59), d(2026, 7, 9, 10, 1)));
    }

    @Test
    void dailyFirstRunAtStartTimeThenEveryNDays() {
        SchedulePlan first = plan(DmSchedularType.DAILY, DmRecurrenceUnit.DAYS, d(2026, 7, 1, 8, 30), null, null, null, null, null, null);
        assertTrue(ScheduleEvaluator.shouldExecute(first, d(2026, 7, 9, 8, 29), d(2026, 7, 9, 8, 31)));
        assertFalse(ScheduleEvaluator.shouldExecute(first, d(2026, 7, 9, 8, 31), d(2026, 7, 9, 8, 32)));
        SchedulePlan later = first.withLastExecution(d(2026, 7, 8, 8, 30));
        assertTrue(ScheduleEvaluator.shouldExecute(later, d(2026, 7, 9, 8, 29), d(2026, 7, 9, 8, 31)));
    }

    @Test
    void expiredScheduleStopsAfterFirstRun() {
        SchedulePlan p = new SchedulePlan(1, "t", true, DmSchedularType.DAILY, DmRecurrenceUnit.DAYS, 1,
                d(2026, 7, 1, 8, 30), d(2026, 7, 5, 0, 0), true, d(2026, 7, 8, 8, 30), null, null, null, null, null,
                DmWebJobTypes.EMAIL, 1, 1, 1, "1", "db", "fdb", null);
        assertFalse(ScheduleEvaluator.shouldExecute(p, d(2026, 7, 9, 8, 29), d(2026, 7, 9, 8, 31)));
    }

    @Test
    void weeklyNeedsMatchingWeekday() {
        // 2026-07-09 is a Thursday
        SchedulePlan thu = plan(DmSchedularType.WEEKLY, DmRecurrenceUnit.WEEKS, d(2026, 7, 1, 9, 0), null,
                List.of(DmWeekdays.THURSDAY), null, null, null, null);
        SchedulePlan fri = plan(DmSchedularType.WEEKLY, DmRecurrenceUnit.WEEKS, d(2026, 7, 1, 9, 0), null,
                List.of(DmWeekdays.FRIDAY), null, null, null, null);
        assertTrue(ScheduleEvaluator.shouldExecute(thu, d(2026, 7, 9, 8, 59), d(2026, 7, 9, 9, 1)));
        assertFalse(ScheduleEvaluator.shouldExecute(fri, d(2026, 7, 9, 8, 59), d(2026, 7, 9, 9, 1)));
    }

    @Test
    void monthlyByDateIncludingLastDay() {
        SchedulePlan on9 = plan(DmSchedularType.MONTHLY, DmRecurrenceUnit.MONTHS, d(2026, 1, 1, 7, 0), null, null,
                List.of(DmMonths.JULY), List.of(DmDates.DATE_9), null, "d");
        assertTrue(ScheduleEvaluator.shouldExecute(on9, d(2026, 7, 9, 6, 59), d(2026, 7, 9, 7, 1)));
        assertFalse(ScheduleEvaluator.shouldExecute(on9, d(2026, 7, 10, 6, 59), d(2026, 7, 10, 7, 1)));
        SchedulePlan last = plan(DmSchedularType.MONTHLY, DmRecurrenceUnit.MONTHS, d(2026, 1, 1, 7, 0), null, null,
                List.of(DmMonths.JULY), List.of(DmDates.DATE_LAST), null, "d");
        assertTrue(ScheduleEvaluator.shouldExecute(last, d(2026, 7, 31, 6, 59), d(2026, 7, 31, 7, 1)));
        assertFalse(ScheduleEvaluator.shouldExecute(last, d(2026, 7, 30, 6, 59), d(2026, 7, 30, 7, 1)));
    }

    @Test
    void monthlyLastWeekMatchesNow() {
        // Friday 2026-07-31 is in the last week of July 2026
        SchedulePlan p = plan(DmSchedularType.MONTHLY, DmRecurrenceUnit.MONTHS, d(2026, 1, 1, 7, 0), null,
                List.of(DmWeekdays.FRIDAY), List.of(DmMonths.JULY), null, List.of(DmWeeks.LAST), "m");
        assertTrue(ScheduleEvaluator.shouldExecute(p, d(2026, 7, 31, 6, 59), d(2026, 7, 31, 7, 1)));
        assertFalse(ScheduleEvaluator.shouldExecute(p, d(2026, 7, 10, 6, 59), d(2026, 7, 10, 7, 1)));
    }
}
