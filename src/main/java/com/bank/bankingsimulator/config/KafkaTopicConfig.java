package com.bank.bankingsimulator.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic-json.name}")
    private String topicName;
    @Bean
    public NewTopic eventsTopic(){
        return TopicBuilder.name(topicName)
                .build();
    }

    @Bean
    public NewTopic javaguidesJsonTopic(){
        return TopicBuilder.name("javaguides_json")
                .build();
    }
}
