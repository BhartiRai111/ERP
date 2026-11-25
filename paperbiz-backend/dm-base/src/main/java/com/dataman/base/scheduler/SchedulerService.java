package com.dataman.base.scheduler;

import com.dataman.base.enums.DmWebJobTypes;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Holds the active schedules and, every minute, starts the ones that are due (legacy DmSchedulerFactory).
 * Each run goes to the {@link ScheduledJobHandler} of its job type on a worker thread; one failing job never
 * stops the others. Starting/stopping the watch and listing work as before.
 */
@Service
public class SchedulerService {

    private static final Logger log = LoggerFactory.getLogger(SchedulerService.class);

    private final Map<Long, SchedulePlan> plans = new ConcurrentHashMap<>();
    private final Map<DmWebJobTypes, ScheduledJobHandler> handlers = new EnumMap<>(DmWebJobTypes.class);
    private final TaskExecutor executor;
    private final AtomicBoolean watching;
    private volatile Date lastTick = new Date();

    public SchedulerService(List<ScheduledJobHandler> jobHandlers, TaskExecutor applicationTaskExecutor,
            @Value("${dataman.scheduler.enabled:true}") boolean enabled) {
        jobHandlers.forEach(h -> handlers.put(h.jobType(), h));
        this.executor = applicationTaskExecutor;
        this.watching = new AtomicBoolean(enabled);
    }

    /** Add or replace a schedule; an inactive one is removed (legacy schedule()). */
    public void schedule(SchedulePlan plan) {
        if (plan.active()) {
            plans.put(plan.docId(), plan);
        } else {
            plans.remove(plan.docId());
        }
    }

    public void remove(long docId) {
        plans.remove(docId);
    }

    public void clear() {
        plans.clear();
    }

    public Collection<SchedulePlan> list() {
        return List.copyOf(plans.values());
    }

    public void stopWatching() {
        watching.set(false);
    }

    public void startWatching() {
        watching.set(true);
        lastTick = new Date();
    }

    @Scheduled(fixedDelayString = "${dataman.scheduler.tick-millis:60000}")
    public void tick() {
        tick(new Date());
    }

    /** One tick for the window (last tick, now]; public for tests. Returns the plans started. */
    public List<SchedulePlan> tick(Date now) {
        Date previous = lastTick;
        lastTick = now;
        if (!watching.get()) {
            return List.of();
        }
        List<SchedulePlan> due = plans.values().stream()
                .filter(p -> ScheduleEvaluator.shouldExecute(p, previous, now)).toList();
        for (SchedulePlan p : due) {
            ScheduledJobHandler h = handlers.get(p.jobType());
            if (h == null) {
                log.warn("No handler for job type {} (schedule {})", p.jobType(), p.name());
                continue;
            }
            executor.execute(() -> {
                try {
                    h.run(p);
                } catch (Exception e) {
                    log.error("Schedule '{}' failed", p.name(), e);
                }
            });
        }
        return due;
    }
}
