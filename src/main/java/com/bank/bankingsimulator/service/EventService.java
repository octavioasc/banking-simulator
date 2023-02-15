package com.bank.bankingsimulator.service;

import com.bank.bankingsimulator.model.Event;
import com.bank.bankingsimulator.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventService {
    private final EventRepository eventRepository;

    private final BankingService bankingService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    public EventService(EventRepository eventRepository, BankingService bankingService) {
        this.eventRepository = eventRepository;
        this.bankingService = bankingService;
    }


    public float getBalanceFromEvents(Long id) throws Exception {
        float balance;
        try{
            bankingService.initializeBalance(id);
            reproduceEvents(id);
            balance = bankingService.getBalance(id);
        }catch (Exception e){
            throw e;
        }
        LOGGER.info(String.format("getBalance: " + balance));
        return balance;
    }

    private void reproduceEvents(Long id) throws Exception {
        List<Event> events = eventRepository.findAll();
        for (Event e : events) {
            if (e.getName().equals("deposit")) {
                bankingService.deposit(id, Float.valueOf(e.getValue()));
            } else if (e.getName().equals("withdraw")) {
                bankingService.withdraw(id, Float.valueOf(e.getValue()));
            } else if (e.getName().equals("addInterest")) {
                bankingService.addInterest(id, Float.valueOf(e.getValue()));
            }
        }
    }
}
