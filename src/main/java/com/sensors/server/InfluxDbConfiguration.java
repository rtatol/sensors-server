package com.sensors.server;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import static java.util.Objects.nonNull;

@Component
@ConfigurationProperties("influxDb")
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
