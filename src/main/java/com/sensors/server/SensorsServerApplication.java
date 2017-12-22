package com.sensors.server;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
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

        influxDB.createDatabase(configuration.getDatabase());
        influxDB.enableBatch(100, 500, TimeUnit.MILLISECONDS);
        influxDB.enableGzip();

        return influxDB;
    }

    @Profile("test")
    @Bean
    public InfluxDB influxDbFake() {
        return new InfluxDbFake();
    }
}
