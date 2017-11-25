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
@SuppressWarnings("unused")
public class GaugesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GaugesController.class);

    private final StorageService storageService;

    @Autowired
    GaugesController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/")
    public ResponseEntity gauge(@RequestBody @Valid final Gauges gauges) {
        LOGGER.info(gauges.toString());
        storageService.save(gauges);

        return ResponseEntity.ok().build();
    }
}
