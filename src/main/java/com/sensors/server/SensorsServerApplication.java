package com.sensors.server;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(InfluxDbConfiguration.class)
public class SensorsServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SensorsServerApplication.class, args);
    }

    @Profile("!test")
    @Bean
    public InfluxDB influxDb(final InfluxDbConfiguration configuration) {

        final InfluxDB influxDB;

        if (configuration.withCredentials()) {
            influxDB = InfluxDBFactory.connect(configuration.getUrl(), configuration.getUser(), configuration.getPassword());
        } else {
            influxDB = InfluxDBFactory.connect(configuration.getUrl());
        }

        final String createDatabaseQueryString = String.format("CREATE DATABASE \"%s\"", configuration.getDatabase());
        final Query createDbQuery = new Query(createDatabaseQueryString, configuration.getDatabase());
        final QueryResult result = influxDB.query(createDbQuery);

        if (result.hasError()) {
            log.error("Error during query execution: {}", result.getError());
        }
        log.info("Create DB query result: {}", result.toString());

        influxDB.enableBatch(100, 500, TimeUnit.MILLISECONDS);
        influxDB.enableGzip();

        return influxDB;
    }

    @Profile("test")
    @Bean
    public InfluxDB influxDbFake() {
        return new InfluxDbFake();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {

        final CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(true);
        filter.setIncludeClientInfo(true);
        filter.setMaxPayloadLength(2000);
        filter.setAfterMessagePrefix("Request received: ");

        return filter;
    }
}
