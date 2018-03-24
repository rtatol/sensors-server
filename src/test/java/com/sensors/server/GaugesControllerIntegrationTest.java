package com.sensors.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GaugesControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_store_gauges() {
        // given
        final Map<String, Double> gauges = new HashMap<>(4);
        gauges.put("humidity", 30.00);
        gauges.put("temperature", 500.00);
        gauges.put("pressure", 997.69);
        gauges.put("lux", 8.0);

        final Gauges request = new Gauges("deviceID", gauges);

        // when
        final ResponseEntity responseEntity = restTemplate.postForEntity("/", request, Object.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void should_throw_exception_when_request_is_incorrect() {
        // given
        final Gauges request = new Gauges("deviceID", null);

        // when
        final ResponseEntity responseEntity = restTemplate.postForEntity("/", request, Object.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}