package com.nsv.pet.postgre.shard.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Qualifier("hotDataSource")
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.hotdb")
    DataSource hotDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("hotJdbcTemplate")
    JdbcTemplate hotJdbcTemplate(@Qualifier("hotDataSource") DataSource hotDataSource) {
        return new JdbcTemplate(hotDataSource);
    }


    @Bean
    @Qualifier("coldDataSource")
    @ConfigurationProperties(prefix = "app.datasource.colddb")
    DataSource coldDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("coldJdbcTemplate")
    JdbcTemplate coldJdbcTemplate(@Qualifier("coldDataSource") DataSource coldDataSource) {
        return new JdbcTemplate(coldDataSource);
    }

}
