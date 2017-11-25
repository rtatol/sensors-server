package com.sensors.server;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class Gauges {

    @NotEmpty
    private final String deviceId;

    @NotNull
    private final Map<String, Double> gauges;
}
