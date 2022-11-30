package com.bank.bankingsimulator.service;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void save (Account account){
        accountRepository.save(account);
    }

    public float getBalance(String idAccount){
        return accountRepository.findById(idAccount).get().getBalance();
    }
    public float makeDeposit(String idAccount, float amount){
        Account a = accountRepository.findById(idAccount).get();
        float total = a.getBalance() + amount;
        a.setBalance(total);
        accountRepository.save(a);
        return total;
    }

}
