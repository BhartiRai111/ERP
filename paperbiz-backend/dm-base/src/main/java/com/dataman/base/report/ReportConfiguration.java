package com.dataman.base.report;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ReportProperties.class)
public class ReportConfiguration {
}
