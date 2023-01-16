package com.camelcase.consumer;

import com.camelcase.entity.StreamData;
import com.camelcase.service.StreamDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Ravi Panchal
 */
@Slf4j
@Service
public class KafkaConsumer {

    @Autowired
    private StreamDataService streamDataService;

    @KafkaListener(topics = "event_data_stream", groupId = "dataStreamGroup")
    public void consumer(String eventMessage){
        log.info("Event message received: {}",eventMessage);
        StreamData streamData = new StreamData();
        streamData.setEventData(eventMessage);
        streamDataService.saveConsumedData(streamData);
    }

}
