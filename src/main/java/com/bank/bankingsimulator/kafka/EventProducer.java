package com.bank.bankingsimulator.kafka;

import com.bank.bankingsimulator.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);

    private KafkaTemplate<String, Event> kafkaTemplate;

    public EventProducer(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void newEvent(Event data){
        LOGGER.info(String.format("Message send -> %s", data));
        Message<Event> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "events")
                .build();

        kafkaTemplate.send(message);
    }
}
