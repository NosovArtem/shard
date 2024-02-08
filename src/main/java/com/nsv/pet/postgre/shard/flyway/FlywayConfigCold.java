package com.nsv.pet.postgre.shard.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@FlywayDataSource
public class FlywayConfigCold {

    @Bean(initMethod = "migrate")
    public Flyway flyway2(@Qualifier("coldDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration/db2")
                .baselineOnMigrate(true)
                .load();
        return flyway;
    }
}
