package com.camelcase.service.impl;

import com.camelcase.eventhandler.DataStreamEventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * @author Ravi Panchal
 */
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Value("${stream.data.topic_name}")
    private String topic;

    @Value("${stream.data.uri}")
    private String sourceUri;

    public void sendMessage(){
        BackgroundEventHandler eventHandler = new DataStreamEventHandler(topic,kafkaTemplate);
        EventSource.Builder builder = new EventSource.Builder(URI.create(sourceUri));
        try(BackgroundEventSource source = new BackgroundEventSource.Builder(eventHandler,builder).build();){
            source.start();
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
