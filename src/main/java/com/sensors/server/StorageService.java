package com.sensors.server;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class StorageService {

    private final InfluxDB influxDB;
    private final InfluxDbConfiguration configuration;

    @Autowired
    StorageService(final InfluxDB influxDB, final InfluxDbConfiguration configuration) {
        this.influxDB = influxDB;
        this.configuration = configuration;
    }

    void save(final Gauges gauges) {

        final Point.Builder pointBuilder = Point.measurement("gauge")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("deviceId", gauges.getDeviceId());

        gauges.getGauges().forEach((name, value) -> {
            if (nonNull(value)) {
                pointBuilder.addField(name, value);
            } else {
                log.warn("Invalid gauge, name: {}, value: {}", name, value);
            }
        });

        influxDB.write(configuration.getDatabase(), "autogen", pointBuilder.build());
    }
}
