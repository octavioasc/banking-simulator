package com.bank.bankingsimulator.service;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.model.Event;
import com.bank.bankingsimulator.repository.AccountRepository;
import com.bank.bankingsimulator.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService   {
    private final AccountRepository accountRepository;
    private final EventRepository eventRepository;

    public AccountService(AccountRepository accountRepository,
                          EventRepository eventRepository) {
        this.accountRepository = accountRepository;
        this.eventRepository = eventRepository;
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
                eventRepository.save(event);
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
            eventRepository.save(event);
        }catch (Exception e){
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
            eventRepository.save(event);
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
            eventRepository.save(event);
        }catch (Exception e){
            throw e;
        }
        return total;
    }


}
