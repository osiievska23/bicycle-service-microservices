package org.vosiievska.bicycle.service.workshop.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("org.vosiievska.bicycle.service.workshop.dataaccess.repository")
@EntityScan("org.vosiievska.bicycle.service.dataaccess.jpa.entity")
public class DataSourceConfig {
}
