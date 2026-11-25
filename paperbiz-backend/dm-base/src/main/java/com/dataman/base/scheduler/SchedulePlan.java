package com.dataman.base.scheduler;

import com.dataman.base.enums.DmDates;
import com.dataman.base.enums.DmMonths;
import com.dataman.base.enums.DmRecurrenceUnit;
import com.dataman.base.enums.DmSchedularType;
import com.dataman.base.enums.DmWebJobTypes;
import com.dataman.base.enums.DmWeekdays;
import com.dataman.base.enums.DmWeeks;
import java.util.Date;
import java.util.List;

/**
 * One schedule (a row of schedulerMast, legacy SchedulerMast_Mdl): when to run, what to run
 * (job type + template id) and for which company/site it runs.
 */
public record SchedulePlan(
        long docId,
        String name,
        boolean active,
        DmSchedularType type,
        DmRecurrenceUnit recurUnit,
        int recurQuantity,
        Date startDateTime,
        Date expiryDateTime,
        boolean expiryUsed,
        Date lastExecutionTime,
        /** "d" = by dates, "m" = by week-of-month + weekday (monthly schedules). */
        String dayOrMonth,
        List<DmWeekdays> days,
        List<DmMonths> months,
        List<DmDates> dates,
        List<DmWeeks> weeks,
        DmWebJobTypes jobType,
        long templateId,
        int pltCode,
        int companyCode,
        String voucherPrefix,
        String activeDbName,
        String activeFileDbName,
        List<Integer> sites) {

    public SchedulePlan {
        days = days == null ? List.of() : List.copyOf(days);
        months = months == null ? List.of() : List.copyOf(months);
        dates = dates == null ? List.of() : List.copyOf(dates);
        weeks = weeks == null ? List.of() : List.copyOf(weeks);
        sites = sites == null ? List.of() : List.copyOf(sites);
    }

    /** Same plan after a run (the job handler stores this time in schedulerMast). */
    public SchedulePlan withLastExecution(Date last) {
        return new SchedulePlan(docId, name, active, type, recurUnit, recurQuantity, startDateTime, expiryDateTime,
                expiryUsed, last, dayOrMonth, days, months, dates, weeks, jobType, templateId, pltCode, companyCode,
                voucherPrefix, activeDbName, activeFileDbName, sites);
    }
}
