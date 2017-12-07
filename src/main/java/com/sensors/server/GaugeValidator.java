package com.sensors.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class GaugeValidator {

    public boolean isValid(final String name, final Double value) {
        final GaugeValidation gaugeValidation = GaugeValidation.from(name);
        return gaugeValidation.isValid(value);
    }

    @AllArgsConstructor
    @Getter
    enum GaugeValidation {

        TEMPERATURE(-100.0, 85.0),
        HUMIDITY(0.0, 100.0),
        PRESSURE(900.0, 1100.0),
        LUX(0.0, 100_000.0),
        UNKNOWN(-Double.MAX_VALUE, Double.MAX_VALUE);

        private final Double min;
        private final Double max;

        public static GaugeValidation from(final String gaugeName) {
            for (final GaugeValidation gauge : values()) {
                if (gauge.name().equalsIgnoreCase(gaugeName)) {
                    return gauge;
                }
            }
            return UNKNOWN;
        }

        public boolean isValid(final Double value) {
            return nonNull(value) && max > value && min < value;
        }
    }
}


