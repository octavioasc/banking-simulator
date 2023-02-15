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
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankingServiceTest {

/*    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private BankingService bankingService;

    @Test
    void getBalanceAccount() throws Exception {
        Account account = new Account(500, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        float balance = bankingService.getBalance(account.getId());
        assertEquals(account.getBalance(),balance);
    }

    @Test
    void makeDepositAccount() throws Exception {
        float initBalance = 500;
        float amount = 1000;
        Account account = new Account(initBalance, "Available");
        lenient().when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(bankingService.deposit(account.getBalance(), amount), initBalance + amount);
    }

    @Test
    void getFromAccount() throws Exception {
        float initBalance = 500;
        float amount = 200;
        Account account = new Account(initBalance, "Available");
        lenient().when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(bankingService.withdraw(account.getBalance(), amount), initBalance - amount);
    }

    @Test
    void addInterestAccount() throws Exception {
        float initBalance = 500;
        float percent = 1; //1%
        Account account = new Account(initBalance, "Available");
        lenient().when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(bankingService.addInterest(account.getBalance(), percent), initBalance + (initBalance * (percent / 100)) );
    }*/
   //@Test
    /**
     * Escenario1: Depósito(1000); Depósito(1000); Intereses(10 %); Consulta de Saldo => (2200)
     */

/*    void userCase1() throws Exception {
        float initBalance = 0;
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        bankingService.deposit(account.getBalance(), 1000F);
        bankingService.deposit(account.getBalance(),1000F);
        bankingService.addInterest(account.getBalance(),10F);
        assertEquals(bankingService.getBalance(account.getId()), 2200F);
    }*/

  //  @Test

    /**
     * Escenario2: Depósito(1000); Intereses(10 %); Depósito(1000); Consulta de Saldo => (2100)
     */
/*    void userCase2() throws Exception{
        float initBalance = 0;
        Account account = new Account(initBalance, "Available");
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        bankingService.deposit(account.getBalance(),1000F);
        bankingService.addInterest(account.getBalance(),10F);
        bankingService.deposit(account.getBalance(),1000F);
        assertEquals(bankingService.getBalance(account.getId()), 2100F);
    }*/
}


