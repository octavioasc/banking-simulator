package com.bank.bankingsimulator.service;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService accountService;

    @Test
    void getBalanceAccount() throws Exception {
        Account account = new Account(500, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        float balance = accountService.getBalanceAcount(account.getId());
        assertEquals(account.getBalance(),balance);
    }

    @Test
    void makeDepositAccount() throws Exception {
        float initBalance = 500;
        float amount = 1000;
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(accountService.makeDepositAccount(account.getId(), amount), initBalance + amount);
    }

    @Test
    void getFromAccount() throws Exception {
        float initBalance = 500;
        float amount = 200;
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(accountService.getFromAccount(account.getId(), amount), initBalance - amount);
    }

    @Test
    void addInterestAccount() throws Exception {
        float initBalance = 500;
        float percent = 1; //1%
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(accountService.addInterestAccount(account.getId(), percent), initBalance + (initBalance * (percent / 100)) );
    }
}
