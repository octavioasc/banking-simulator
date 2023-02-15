package com.bank.bankingsimulator.service;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankingService {
    private final AccountRepository accountRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BankingService.class);

    public BankingService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public float getBalance(Long id) throws Exception{
        Account a;
        float balance = 0;
        try{
            Optional<Account> result = accountRepository.findById(id);
            if(!result.isPresent())
                throw new Exception();
            a = result.get();
            balance = a.getBalance();
        }catch (Exception e){
            throw e;
        }
        return balance;
    }

    public void initializeBalance(Long id) throws Exception{
        Account a;
        float balance = 0;
        try{
            Optional<Account> result = accountRepository.findById(id);
            if(!result.isPresent())
                throw new Exception();
            a = result.get();
            a.setBalance(0);
            accountRepository.save(a);
        }catch (Exception e){
            throw e;
        }
    }


    public float deposit(Long id, Float amount) throws Exception {
        Account a;
        float value;
        try{
            Optional<Account> result = accountRepository.findById(id);
            if(!result.isPresent())
                throw new Exception();
            a = result.get();
            value = a.getBalance() + amount;
            a.setBalance(value);
            accountRepository.save(a);
        }catch (Exception e){
            throw e;
        }
        return value;
    }

    public float withdraw(Long id, Float amount) throws Exception {
        Account a;
        float value;
        try{
            Optional<Account> result = accountRepository.findById(id);
            if(!result.isPresent())
                throw new Exception();
            a = result.get();
            value = getBalance(id) - amount;
            a.setBalance(value);
            accountRepository.save(a);
        }catch (Exception e){
            throw e;
        }
        return value;
    }

    public float addInterest(Long id, Float percent) throws Exception {
        Account a;
        float value, balance;
        try{
            Optional<Account> result = accountRepository.findById(id);
            if(!result.isPresent())
                throw new Exception();
            a = result.get();
            balance = getBalance(id);
            value = balance + (balance * (percent / 100));
            a.setBalance(value);
            accountRepository.save(a);
        }catch (Exception e){
            throw e;
        }
        return value;
    }

}
