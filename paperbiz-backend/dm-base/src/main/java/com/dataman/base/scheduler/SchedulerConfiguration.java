package com.dataman.base.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/** Turns on the one-minute tick of {@link SchedulerService} (switch off with dataman.scheduler.enabled=false). */
@Configuration
@EnableScheduling
public class SchedulerConfiguration {
}
