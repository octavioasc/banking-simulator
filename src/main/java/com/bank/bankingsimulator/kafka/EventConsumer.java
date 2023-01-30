package com.bank.bankingsimulator.kafka;

import com.bank.bankingsimulator.model.Event;
import com.bank.bankingsimulator.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);

    private final EventRepository eventRepository;

    public EventConsumer(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @KafkaListener(topics = "events", groupId = "myGroup")
    public void consume(Event event){
        LOGGER.info(String.format("Json message received -> %s", event.toString()));
        eventRepository.save(event);
    }
}
