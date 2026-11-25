package com.dataman.base.scheduler;

import com.dataman.base.enums.DmDates;
import com.dataman.base.enums.DmMonths;
import com.dataman.base.enums.DmWeekdays;
import com.dataman.base.enums.DmWeeks;
import com.dataman.base.enums.DmRecurrenceUnit;
import com.dataman.base.enums.DmSchedularType;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Decides whether a schedule is due in the tick window (previous, now]. Same rules as the legacy
 * DmAutoScheduler/DmScheduler (ONCE, DAILY, WEEKLY, MONTHLY); the tick runs every minute.
 *
 * <p>One deliberate fix: the monthly "Last" week of the month never matched in legacy (it compared the
 * constant -1 with the number of weeks); here it matches when the day is in the last week of its month.
 */
public final class ScheduleEvaluator {

    private ScheduleEvaluator() {
    }

    public static boolean shouldExecute(SchedulePlan p, Date previous, Date now) {
        if (p.type() == null) {
            return false;
        }
        Calendar prev = cal(previous);
        Calendar cur = cal(now);
        return switch (p.type()) {
            case ONCE -> p.lastExecutionTime() == null && p.startDateTime().before(now) && p.startDateTime().after(previous);
            case DAILY -> p.active() && p.recurUnit() == DmRecurrenceUnit.DAYS && recurring(p, prev, cur, Calendar.DAY_OF_YEAR, false);
            case WEEKLY -> p.active() && p.recurUnit() == DmRecurrenceUnit.WEEKS && weekly(p, prev, cur);
            case MONTHLY -> p.active() && p.recurUnit() == DmRecurrenceUnit.MONTHS && monthly(p, prev, cur);
        };
    }

    /** Daily/weekly: first run on the start time of the day, later runs every N units after the last run. */
    private static boolean recurring(SchedulePlan p, Calendar prev, Calendar now, int unit, boolean weekly) {
        Calendar t = candidate(p, prev, now, unit);
        return t != null && inWindow(t, prev, now);
    }

    private static Calendar candidate(SchedulePlan p, Calendar prev, Calendar now, int unit) {
        Calendar t = new GregorianCalendar();
        if (p.lastExecutionTime() == null) {
            t.setTime(p.startDateTime());
            t.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
            return t;
        }
        if (p.expiryUsed() && p.expiryDateTime().compareTo(now.getTime()) < 0) {
            return null;
        }
        t.setTime(p.lastExecutionTime());
        t.add(unit, p.recurQuantity());
        return t;
    }

    private static boolean weekly(SchedulePlan p, Calendar prev, Calendar now) {
        Calendar t = candidate(p, prev, now, Calendar.WEEK_OF_YEAR);
        if (t == null || !inWindow(t, prev, now)) {
            return false;
        }
        for (DmWeekdays d : p.days()) {
            if (d.getWeekDay() == t.get(Calendar.DAY_OF_WEEK)) {
                return true;
            }
        }
        return false;
    }

    private static boolean monthly(SchedulePlan p, Calendar prev, Calendar now) {
        Calendar t = (Calendar) now.clone();
        if (!dayMatches(p, t)) {
            return false;
        }
        if (p.lastExecutionTime() != null && p.expiryUsed() && p.expiryDateTime().compareTo(now.getTime()) < 0) {
            return false;
        }
        Calendar start = cal(p.startDateTime());
        t.set(Calendar.HOUR_OF_DAY, start.get(Calendar.HOUR_OF_DAY));
        t.set(Calendar.MINUTE, start.get(Calendar.MINUTE));
        t.set(Calendar.SECOND, start.get(Calendar.SECOND));
        return inWindow(t, prev, now);
    }

    private static boolean dayMatches(SchedulePlan p, Calendar t) {
        boolean monthOk = false;
        for (DmMonths m : p.months()) {
            if (m.getMonthCalendarValue() == t.get(Calendar.MONTH)) {
                monthOk = true;
                break;
            }
        }
        if (!monthOk) {
            return false;
        }
        if ("d".equalsIgnoreCase(p.dayOrMonth())) {
            for (DmDates d : p.dates()) {
                if ((d == DmDates.DATE_LAST && t.getActualMaximum(Calendar.DATE) == t.get(Calendar.DATE))
                        || d.getValue() == t.get(Calendar.DATE)) {
                    return true;
                }
            }
        } else if ("m".equalsIgnoreCase(p.dayOrMonth())) {
            for (DmWeeks w : p.weeks()) {
                boolean weekOk = w == DmWeeks.LAST
                        ? t.get(Calendar.WEEK_OF_MONTH) == t.getActualMaximum(Calendar.WEEK_OF_MONTH)
                        : w.getWeekCalendarValue() == t.get(Calendar.WEEK_OF_MONTH);
                if (weekOk) {
                    for (DmWeekdays d : p.days()) {
                        if (d.getWeekDay() == t.get(Calendar.DAY_OF_WEEK)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean inWindow(Calendar t, Calendar prev, Calendar now) {
        return t.compareTo(prev) >= 0 && now.compareTo(t) >= 0;
    }

    private static Calendar cal(Date d) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        return c;
    }
}
