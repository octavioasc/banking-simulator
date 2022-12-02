package com.bank.bankingsimulator.service;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService   {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void saveAccount (Account account){
        accountRepository.save(account);
    }

    public float getBalanceAcount(String idAccount) throws Exception {
            Account a;
            try{
                Optional<Account> result = accountRepository.findById(idAccount);
                if (!result.isPresent())
                    throw new Exception(); // Account not found
                a = result.get();
            }catch (Exception e){
                throw e;
            }
            return a.getBalance() ;
    }

    public float makeDepositAccount(String idAccount, float amount) throws Exception {
        Optional<Account> result = accountRepository.findById(idAccount);
        float total;
        try{
            if (!result.isPresent())
                throw new Exception();// Account not found
            Account a = result.get();
            total = a.getBalance() + amount;
            a.setBalance(total);
            accountRepository.save(a);
        }catch (Exception e){
            throw e;
        }
        return total;
    }

    public float getFromAccount(String idAccount, float amount) throws Exception {
        float total = 0;
        try{
            Optional<Account> result = accountRepository.findById(idAccount);
            if (!result.isPresent())
                throw new Exception();// Account not found
            Account a = result.get();
            total = a.getBalance() - amount;
            a.setBalance(total);
            accountRepository.save(a);
        }catch (Exception e){
            throw e;
        }
        return total;
    }

    public float addInterestAccount(String idAccount, float percent) throws Exception {
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
        }catch (Exception e){
            throw e;
        }
        return total;
    }


}
