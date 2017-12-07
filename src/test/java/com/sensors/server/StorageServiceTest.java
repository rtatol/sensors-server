package com.sensors.server;

import org.assertj.core.util.Maps;
import org.influxdb.InfluxDB;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StorageServiceTest {

    @Mock
    private InfluxDB influxDB;
    @Mock
    private InfluxDbConfiguration configuration;
    @Mock
    private GaugeValidator validator;
    @InjectMocks
    private StorageService storageService;

    @Before
    public void setUp() throws Exception {
        doNothing().when(influxDB).write(any(), any(), any());
    }

    @Test
    public void should_save_all_gauges_when_are_valid() {
        // given
        final String deviceId = "deviceId";
        final String gaugeName = "temperature";
        final Double gaugeValue = 24.55;

        final Gauges gauges = new Gauges(deviceId, Maps.newHashMap(gaugeName, gaugeValue));

        when(validator.isValid(gaugeName, gaugeValue)).thenReturn(true);

        // when
        storageService.save(gauges);

        // then
        verify(influxDB).write(any(), any(), any());

    }
}