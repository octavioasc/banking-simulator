package com.bank.bankingsimulator.kafka;

import com.bank.bankingsimulator.model.Event;
import com.bank.bankingsimulator.repository.EventRepository;
import com.bank.bankingsimulator.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);

    private final EventRepository eventRepository;
    private final AccountService accountService;

    public EventConsumer(EventRepository eventRepository, AccountService accountService) {
        this.eventRepository = eventRepository;
        this.accountService = accountService;
    }

    @KafkaListener(topics = "events", groupId = "myGroup")
    public void consume(Event event){
        LOGGER.info(String.format("Json message received -> %s", event));
        eventRepository.save(event);
    }
}
