package com.bank.bankingsimulator.service;

import com.bank.bankingsimulator.kafka.EventProducer;
import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.model.Event;
import com.bank.bankingsimulator.repository.AccountRepository;
import com.bank.bankingsimulator.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountProducerService {
    private final AccountRepository accountRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountProducerService.class);
    private EventProducer eventProducer;
    public AccountProducerService(AccountRepository accountRepository,
                                  EventRepository eventRepository, EventProducer kafkaProducer) {
        this.accountRepository = accountRepository;
        this.eventProducer = kafkaProducer;
    }
    public void saveAccount (Account account){
        accountRepository.save(account);
    }

    public float getBalanceAcount(Long idAccount) throws Exception {
            Event event;
            Account a;
            try{
                Optional<Account> result = accountRepository.findById(idAccount);
                if (!result.isPresent())
                    throw new Exception(); // Account not found
                a = result.get();
                event = new Event("getBalance");
                event.setA(a);
                // send to kafka broker.
                eventProducer.sendMessage(event);
            }catch (Exception e){
                throw e;
            }
            return a.getBalance() ;
    }

    public float makeDepositAccount(Long idAccount, float amount) throws Exception {
        Event event;
        Optional<Account> result = accountRepository.findById(idAccount);
        float total;
        try{
            if (!result.isPresent())
                throw new Exception();// Account not found
            Account a = result.get();
            total = a.getBalance() + amount;
            a.setBalance(total);
            accountRepository.save(a);
            event  = new Event("makeDepositAccount",""+amount);
            event.setA(a);
            // send to kafka broker.
            eventProducer.sendMessage(event);
        }catch (Exception e){
            LOGGER.info(String.format("Excepcion: %s", e.getMessage()));
            throw e;
        }
        return total;
    }

    public float getFromAccount(Long idAccount, float amount) throws Exception {
        Event event;
        float total = 0;
        try{
            Optional<Account> result = accountRepository.findById(idAccount);
            if (!result.isPresent())
                throw new Exception();// Account not found
            Account a = result.get();
            total = a.getBalance() - amount;
            a.setBalance(total);
            accountRepository.save(a);
            event = new Event("getFromAccount", ""+amount );
            event.setA(a);
            // send to kafka broker.
            eventProducer.sendMessage(event);
        }catch (Exception e){
            throw e;
        }
        return total;
    }

    public float addInterestAccount(Long idAccount, float percent) throws Exception {
        Event event;
        float total, balance = 0;
        try{
            Optional<Account> result = accountRepository.findById(idAccount);
            if (!result.isPresent())
                throw new Exception();// Account not found
            Account a = result.get();
            balance = a.getBalance();
            total = balance + (balance * (percent / 100))  ;
            a.setBalance(total);
            accountRepository.save(a);
            event = new Event("addInterestAccount",""+percent);
            event.setA(a);
            // send to kafka broker.
            eventProducer.sendMessage(event);
        }catch (Exception e){
            throw e;
        }
        return total;
    }


}
