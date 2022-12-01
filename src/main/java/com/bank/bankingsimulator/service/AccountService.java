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

    public void saveAccount (Account account){
        accountRepository.save(account);
    }

    public float getBalanceAcount(String idAccount){
        return accountRepository.findById(idAccount).get().getBalance();
    }

    /**
     *
     * @param idAccount
     * @param amount
     * @return total
     */
    public float makeDepositAccount(String idAccount, float amount){
        Account a = accountRepository.findById(idAccount).get();
        float total = a.getBalance() + amount;
        a.setBalance(total);
        accountRepository.save(a);
        return total;
    }

    /**
     *
     * @param idAccount
     * @param amount
     * @return total
     */
    public float getFromAccount(String idAccount, float amount){
        Account a = accountRepository.findById(idAccount).get();
        float total = a.getBalance() - amount;
        a.setBalance(total);
        accountRepository.save(a);
        return total;
    }

    public float addInterestAccount(String idAccount, float percent){
        Account a = accountRepository.findById(idAccount).get();
        float balance = a.getBalance();
        float total = balance + (balance * (percent / 100))  ;
        a.setBalance(total);
        accountRepository.save(a);
        return total;
    }


}
