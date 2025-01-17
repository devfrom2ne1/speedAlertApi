package org.dev2ne1.speedalertapi.controller;

import lombok.RequiredArgsConstructor;
import org.dev2ne1.speedalertapi.service.TrafficCameraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/speed")
public class TrafficCameraController {

    private final TrafficCameraService trafficCameraService;

    @GetMapping("/check")
    public ResponseEntity<String> getTrafficCameras(@RequestParam String latitude, @RequestParam String longitude) {
        try {
            String cameraData = trafficCameraService.getTrafficCameraData( latitude, longitude);
            return ResponseEntity.ok(cameraData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while calling the external API: " + e.getMessage());
        }
    }
}
