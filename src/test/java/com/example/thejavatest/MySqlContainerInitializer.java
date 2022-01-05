package com.example.thejavatest;

import java.util.Map;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class MySqlContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse("mysql:5.7.34"))
            .withEnv("TZ", "Asia/Seoul")
            .withEnv("MYSQL_DATABASE", "test")
            .withEnv("MYSQL_USER", "test")
            .withEnv("MYSQL_PASSWORD", "test")
            .withEnv("MYSQL_ROOT_PASSWORD", "test")
            .withCommand(
                "--character-set-server=utf8mb4",
                "--collation-server=utf8mb4_unicode_ci",
                "--skip-character-set-client-handshake",
                "--default-time-zone=+09:00"
            )
//            .withClasspathResourceMapping("init.sql", "/docker-entrypoint-initdb.d/init.sql", BindMode.READ_ONLY)
            .withExposedPorts(3306)
            ;

        container.start();

        Map<String, String> properties = Map.of(
            "spring.datasource.url", String.format("jdbc:mysql://%s:%d/test?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", container.getHost(), container.getFirstMappedPort()),
            "spring.datasource.username", "test",
            "spring.datasource.password", "test",
            "spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver"
        );
//
        TestPropertyValues.of(properties).applyTo(context.getEnvironment());
    }
}