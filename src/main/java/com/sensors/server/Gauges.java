package com.sensors.server;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Value
class Gauges {

    @NotEmpty
    private final String deviceId;

    @NotNull
    private final Map<String, Double> gauges;
}
