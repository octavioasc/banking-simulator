package com.bank.bankingsimulator.controller;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.repository.AccountRepository;
import com.bank.bankingsimulator.service.BankingService;
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
public class TransactionsControllerTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private BankingService bankingService;

    private TransactionsController transactionsController;

    @Test
    void getBalance() {
        float initBalance = 500;
        Account account = new Account(initBalance);
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        assertEquals(initBalance, Double.valueOf(transactionsController.getBalance(account.getId()).getBody()));
    }

    @Test
    void deposit() throws Exception {
        float initBalance = 500;
        float amount = 1000;
        Account account = new Account(initBalance);
        lenient().when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        assertEquals(initBalance + amount, Double.valueOf(transactionsController.deposit(account.getId(), amount).getBody()));
    }

    @Test
    void withdraw() throws Exception {
        float initBalance = 500;
        float amount = 200;
        Account account = new Account(initBalance);
        lenient().when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        assertEquals(initBalance - amount, Double.valueOf(transactionsController.withdraw(account.getId(), amount).getBody()));
    }

    @Test
    void addInterest() throws Exception {
        float initBalance = 500;
        float percent = 1; //1%
        Account account = new Account(initBalance);
        lenient().when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        assertEquals(initBalance + (initBalance * (percent / 100)), Double.valueOf(transactionsController.addInterest(account.getId(), percent).getBody()));
    }

    @Test
    /**
     * Escenario1: Depósito(1000); Depósito(1000); Intereses(10 %); Consulta de Saldo => (2200)
     */

    void userCase1() throws Exception {
        float initBalance = 0;
        Account account = new Account(initBalance);
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        transactionsController.deposit(account.getId(), 1000F);
        transactionsController.deposit(account.getId(),1000F);
        transactionsController.addInterest(account.getId(),10F);
        assertEquals(Double.valueOf(transactionsController.getBalance(account.getId()).getBody()), 2200F);
    }

    @Test
    /**
     * Escenario2: Depósito(1000); Intereses(10 %); Depósito(1000); Consulta de Saldo => (2100)
     */
    void userCase2() throws Exception{
        float initBalance = 0;
        Account account = new Account(initBalance);
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        transactionsController.deposit(account.getId(),1000F);
        transactionsController.addInterest(account.getId(),10F);
        transactionsController.deposit(account.getId(),1000F);
        assertEquals(Double.valueOf(transactionsController.getBalance(account.getId()).getBody()), 2100F);
    }
}
