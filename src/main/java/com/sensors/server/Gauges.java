package com.sensors.server;

import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Value
class Gauges {

    @NotEmpty
    private final String deviceId;

    @NotNull
    private final Map<String, Double> gauges;
}
