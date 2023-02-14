package com.bank.bankingsimulator.controller;

import com.bank.bankingsimulator.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getBalance/{id}")
    public ResponseEntity<String> getBalanceAccount(@PathVariable(name = "id") Long id){
        float balance;
        try{
            balance = accountService.getBalanceAcount(id);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception getBalanceAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + balance);
    }
}