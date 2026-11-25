package com.dataman.base.scheduler;

import com.dataman.base.enums.DmWebJobTypes;

/**
 * Runs a due schedule. One bean per job type (EMAIL, SMS, WHATSAPP, EVENT ...), provided by the module that
 * knows how (dm-web-jobs). The handler also stores the new last-execution time in schedulerMast and may call
 * {@link SchedulerService#schedule(SchedulePlan)} with the updated plan.
 */
public interface ScheduledJobHandler {

    DmWebJobTypes jobType();

    void run(SchedulePlan plan) throws Exception;
}
