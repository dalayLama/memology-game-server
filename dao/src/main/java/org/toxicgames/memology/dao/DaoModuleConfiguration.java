package org.toxicgames.memology.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.toxicgames.memology.dao.entities")
@EnableJpaRepositories(basePackages = "org.toxicgames.memology.dao.repositories")
public class DaoModuleConfiguration {
}
