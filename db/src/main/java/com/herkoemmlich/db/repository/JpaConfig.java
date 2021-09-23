package com.herkoemmlich.db.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Base64;

@Configuration
@Slf4j
public class JpaConfig {

    @Value("${ipt.datasource.url}")
    String url;

    @Value("${ipt.datasource.username}")
    String username;

    @Value("classpath:db-passwort.txt")
    private Resource dbPasswort;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(getBase64DecodedPassword());
        return dataSourceBuilder.build();
    }

    private String getBase64DecodedPassword() {
        return new String(Base64.getDecoder().decode(getPasswordFromFile()));
    }

    private String getPasswordFromFile() {
        String base6encodedPassword;
        try {
            base6encodedPassword = new String(dbPasswort.getInputStream().readAllBytes());
        } catch (IOException e) {
            log.error("db-password file could not be read", e);
            throw new RuntimeException("db-password file could not be read", e);
        }
        return base6encodedPassword.trim();
    }
}
