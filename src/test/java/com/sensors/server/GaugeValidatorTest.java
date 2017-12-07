package com.sensors.server;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class GaugeValidatorTest {

    private final GaugeValidator validator = new GaugeValidator();

    @Test
    @Parameters({
            "temperature | 20.0        | true",
            "temperature | -140.0      | false",
            "temperature | 200.0       | false",
            "humidity    | 50.0        | true",
            "humidity    | -10.0       | false",
            "humidity    | 110.0       | false",
            "pressure    | 999.0       | true",
            "pressure    | 0.0         | false",
            "pressure    | 2000.0      | false",
            "lux         | 900.0       | true",
            "lux         | -10.0       | false",
            "lux         | 200000.0    | false",
            "abcdefgh    | 0.0         | true",
    })
    public void is_gauge_valid(final String name, final Double value, final boolean expected) {
        // when
        final boolean result = validator.isValid(name, value);

        // then
        assertThat(result).isEqualTo(expected);
    }
}