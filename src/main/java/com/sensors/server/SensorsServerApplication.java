package com.sensors.server;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SensorsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorsServerApplication.class, args);
    }

    @Bean
    public InfluxDB influxDb(final InfluxDbConfiguration influxDbConfiguration) {

        final InfluxDB influxDB = InfluxDBFactory.connect(influxDbConfiguration.getUrl());

        influxDB.createDatabase(influxDbConfiguration.getDatabase());
        influxDB.enableBatch(100, 500, TimeUnit.MILLISECONDS);
        influxDB.enableGzip();

        return influxDB;
    }
}
