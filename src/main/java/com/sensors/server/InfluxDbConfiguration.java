package com.sensors.server;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("influxDb")
@Validated
@Data
public class InfluxDbConfiguration {

    @NotBlank
    private String url;

    @NotBlank
    private String database;
}
