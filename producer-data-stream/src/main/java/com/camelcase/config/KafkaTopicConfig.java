package com.camelcase.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Ravi Panchal
 */
@Configuration
public class KafkaTopicConfig {

    @Value("${stream.data.topic_name}")
    private String topicName;

    public NewTopic createTopic(){
        return TopicBuilder.name(topicName).build();
    }

}
