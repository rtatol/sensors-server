package com.sensors.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GaugesController {

    private final StorageService storageService;

    @PostMapping("/")
    public ResponseEntity gauge(@RequestBody @Valid final Gauges gauges) {
        storageService.save(gauges);
        return ResponseEntity.ok().build();
    }
}
