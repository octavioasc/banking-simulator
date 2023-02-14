package com.bank.bankingsimulator.service;

import com.bank.bankingsimulator.kafka.EventProducer;
import com.bank.bankingsimulator.model.Event;
import com.bank.bankingsimulator.repository.AccountRepository;
import com.bank.bankingsimulator.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private final EventRepository eventRepository;

    public AccountService(AccountRepository accountRepository,
                          EventRepository eventRepository, EventProducer kafkaProducer) {
        this.accountRepository = accountRepository;
        this.eventRepository = eventRepository;
    }

    public float getBalanceAcount(Long idAccount) throws Exception {
            float balance=0;
            try{
                List<Event> events = eventRepository.findAll();
                for(Event e: events){
                    if(e.getName().equals("makeDepositAccount")){
                        balance = balance + Float.valueOf(e.getValue());
                    }else if (e.getName().equals("getFromAccount")){
                        balance = balance - Float.valueOf(e.getValue());
                    }else if (e.getName().equals("addInterestAccount")){
                        balance = balance + (balance * (Float.valueOf(e.getValue()) / 100));
                        LOGGER.info(String.format("interest balance: " + balance));
                    }
                }
            }catch (Exception e){
                throw e;
            }
            LOGGER.info(String.format("getBalance: " + balance));
            return balance;
    }


}
