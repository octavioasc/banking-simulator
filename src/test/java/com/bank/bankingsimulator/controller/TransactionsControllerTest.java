package com.bank.bankingsimulator.controller;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.repository.AccountRepository;
import com.bank.bankingsimulator.service.BankingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionsControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsControllerTest.class);

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private BankingService bankingService;

    private TransactionsController transactionsController;

    @Test
    void getBalance() {
        LOGGER.info("Testing: getBalance");
        float initBalance = 500;
        Double balance;
        Account account = new Account(initBalance);
        LOGGER.info(String.format("Cuenta creada con balance: %f",initBalance));
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        balance = Double.valueOf(transactionsController.getBalance(account.getId()).getBody());
        LOGGER.info(String.format("Valor obtenido de operacion getBalance: %f",balance));
        assertEquals(initBalance, balance);
    }

    @Test
    void deposit() throws Exception {
        LOGGER.info("Testing: deposit");
        float initBalance = 500;
        float amount = 1000;
        Double balance;
        Account account = new Account(initBalance);
        LOGGER.info(String.format("Cuenta creada con balance: %f",initBalance));
        lenient().when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        LOGGER.info(String.format("Monto a depositar: %f",amount));
        balance = Double.valueOf(transactionsController.deposit(account.getId(), amount).getBody());
        LOGGER.info(String.format("Valor obtenido de operacion deposit: %f",balance));
        assertEquals(initBalance + amount, balance);
    }

    @Test
    void withdraw() throws Exception {
        LOGGER.info("Testing: withdraw");
        float initBalance = 500;
        float amount = 200;
        Double balance;
        Account account = new Account(initBalance);
        LOGGER.info(String.format("Cuenta creada con balance: %f",initBalance));
        lenient().when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        LOGGER.info(String.format("Monto a extraer: %f",amount));
        balance = Double.valueOf(transactionsController.withdraw(account.getId(), amount).getBody());
        LOGGER.info(String.format("Valor obtenido de operacion withdraw: %f",balance));
        assertEquals(initBalance - amount, balance);
    }

    @Test
    void addInterest() throws Exception {
        LOGGER.info("Testing: addInterest");
        float initBalance = 500;
        float percent = 1; //1%
        Double balance;
        Account account = new Account(initBalance);
        LOGGER.info(String.format("Cuenta creada con balance: %f",initBalance));
        lenient().when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        transactionsController = new TransactionsController(bankingService);
        LOGGER.info(String.format("Interes a aplicar: %f",percent));
        balance = Double.valueOf(transactionsController.addInterest(account.getId(), percent).getBody());
        LOGGER.info(String.format("Valor obtenido de operacion addInterest: %f",balance));
        assertEquals(initBalance + (initBalance * (percent / 100)), balance);
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
