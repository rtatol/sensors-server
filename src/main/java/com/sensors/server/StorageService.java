package com.sensors.server;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StorageService {

    private final InfluxDB influxDB;
    private final InfluxDbConfiguration configuration;

    @Autowired
    StorageService(final InfluxDB influxDB, final InfluxDbConfiguration configuration) {
        this.influxDB = influxDB;
        this.configuration = configuration;
    }

    void save(final Gauge gauge) {

        final Point point = Point.measurement("gauge")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("humidity", gauge.getHumidity())
                .addField("temperature", gauge.getTemperature())
                .build();

        influxDB.write(configuration.getDatabase(), "autogen", point);
    }
}
