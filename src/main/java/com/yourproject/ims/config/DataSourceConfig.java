package com.yourproject.ims.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        String url = System.getenv("DB_URL");
        if (url != null && !url.startsWith("jdbc:")) {
            System.setProperty("DB_URL", "jdbc:postgresql://" + url);
        }
        return DataSourceBuilder.create().build();
    }
}
