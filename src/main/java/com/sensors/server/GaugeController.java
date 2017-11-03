package com.sensors.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class GaugeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GaugeController.class);

    private final StorageService storageService;

    @Autowired
    GaugeController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/")
    public ResponseEntity gauge(@RequestBody @Valid final Gauge gauge) {
        LOGGER.info(gauge.toString());
        storageService.save(gauge);

        return ResponseEntity.ok().build();
    }
}
