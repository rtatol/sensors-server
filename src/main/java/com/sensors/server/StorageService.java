package com.sensors.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StorageService {

    private final InfluxDB influxDB;
    private final InfluxDbConfiguration configuration;
    private final GaugeValidator validator;

    void save(final Gauges gauges) {

        final Point.Builder pointBuilder = Point.measurement("gauge")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("deviceId", gauges.getDeviceId());

        gauges.getGauges().forEach((name, value) -> {
            if (validator.isValid(name, value)) {
                pointBuilder.addField(name, value);
            } else {
                log.warn("Invalid gauge, name: {}, value: {}", name, value);
            }
        });

        influxDB.write(configuration.getDatabase(), "autogen", pointBuilder.build());
    }
}
