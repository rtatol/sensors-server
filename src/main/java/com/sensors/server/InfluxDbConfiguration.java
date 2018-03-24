package com.sensors.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

import static java.util.Objects.nonNull;

@ConfigurationProperties("influxdb")
@Validated
@Data
public class InfluxDbConfiguration {

    @NotBlank
    private String url;
    @NotBlank
    private String database;
    private String user;
    private String password;

    public boolean withCredentials() {
        return nonNull(user);
    }
}
