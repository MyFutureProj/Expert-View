package com.camelcase.eventhandler;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author Ravi Panchal
 */
@Slf4j
public class DataStreamEventHandler implements BackgroundEventHandler {

    private String topic;
    private KafkaTemplate<String,String> kafkaTemplate;

    public DataStreamEventHandler(String topic, KafkaTemplate<String, String> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        log.info("############ Message received ################ : {}",messageEvent.getData());
        kafkaTemplate.send(topic,messageEvent.getData());
        log.info("Data sent on Topic: {}",s);
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
