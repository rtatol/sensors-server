package com.sensors.server;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@SuppressWarnings("unused")
public class GaugesController {

    private final StorageService storageService;

    @Autowired
    GaugesController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/")
    public ResponseEntity gauge(@RequestBody @Valid final Gauges gauges) {
        log.info(gauges.toString());

        storageService.save(gauges);

        return ResponseEntity.ok().build();
    }
}
