package com.sensors.server;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
class Gauge {

    @NotNull
    private Double humidity;

    @NotNull
    private Double temperature;
}
