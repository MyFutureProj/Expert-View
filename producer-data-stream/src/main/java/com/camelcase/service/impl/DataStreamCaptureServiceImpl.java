package com.camelcase.service.impl;

import com.camelcase.service.DataStreamCaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ravi Panchal
 */
@Service
public class DataStreamCaptureServiceImpl implements DataStreamCaptureService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void sendDataToKafkaTopic() {
        kafkaProducer.sendMessage();
    }
}
