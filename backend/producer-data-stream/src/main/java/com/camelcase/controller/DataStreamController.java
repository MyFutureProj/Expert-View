package com.camelcase.controller;

import com.camelcase.service.DataStreamCaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ravi Panchal
 */
@RestController
public class DataStreamController {

    @Autowired
    private DataStreamCaptureService dataStreamCaptureService;

    @GetMapping("/sendDataToKafka")
    public ResponseEntity<String> sendDataToKafka() {
        dataStreamCaptureService.sendDataToKafkaTopic();
        return ResponseEntity.status(HttpStatus.OK)
                .body("Data Received from External resource and Pushed in Kafka");
    }
}
