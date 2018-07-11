package com.multiheaded.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Auditing is the process of monitoring (logging/tracking) of the changes made to our entities
@Configuration
@EnableJpaAuditing
public class AuditingConfig {

}