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
public class AccountProducerServiceTest {

    /*
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountProducerService accountProducerService;

    @Test
    void getBalanceAccount() throws Exception {
        Account account = new Account(500, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        float balance = accountProducerService.getBalanceAcount(account.getId());
        assertEquals(account.getBalance(),balance);
    }

    @Test
    void makeDepositAccount() throws Exception {
        float initBalance = 500;
        float amount = 1000;
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(accountProducerService.makeDepositAccount(account.getId(), amount), initBalance + amount);
    }

    @Test
    void getFromAccount() throws Exception {
        float initBalance = 500;
        float amount = 200;
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(accountProducerService.getFromAccount(account.getId(), amount), initBalance - amount);
    }

    @Test
    void addInterestAccount() throws Exception {
        float initBalance = 500;
        float percent = 1; //1%
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(accountProducerService.addInterestAccount(account.getId(), percent), initBalance + (initBalance * (percent / 100)) );
    }
*/
   //@Test
    /**
     * Escenario1: Depósito(1000); Depósito(1000); Intereses(10 %); Consulta de Saldo => (2200)
     */

    /*
    void userCase1() throws Exception {
        float initBalance = 0;
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        accountProducerService.makeDepositAccount(account.getId(),1000);
        accountProducerService.makeDepositAccount(account.getId(),1000);
        accountProducerService.addInterestAccount(account.getId(),10);
        assertEquals(accountProducerService.getBalanceAcount(account.getId()), 2200);
    }
*/

  //  @Test
    /**
     * Escenario2: Depósito(1000); Intereses(10 %); Depósito(1000); Consulta de Saldo => (2100)
     */
     /*
    void userCase2() throws Exception{
        float initBalance = 0;
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        accountProducerService.makeDepositAccount(account.getId(),1000);
        accountProducerService.addInterestAccount(account.getId(),10);
        accountProducerService.makeDepositAccount(account.getId(),1000);
        assertEquals(accountProducerService.getBalanceAcount(account.getId()), 2100);
    }
      */
}


