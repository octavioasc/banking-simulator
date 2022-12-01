package com.bank.bankingsimulator.controller;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    private final AccountService accountService ;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/accounts")
    public void saveAccount(@RequestBody Account account){
        accountService.saveAccount(account);
    }

    @GetMapping("/getBalance/{id}")
    public float getBalanceAccount(@PathVariable(name = "id") String id){
        return  accountService.getBalanceAcount(id);
    }

    @PostMapping("/makeDeposit")
    public float makeDepositAccount(@RequestParam String id, @RequestParam float amount){
        return accountService.makeDepositAccount(id, amount);
    }

    @PostMapping("/getFromAccount")
    public float getFromAccount(@RequestParam String id, @RequestParam float amount){
        return accountService.getFromAccount(id, amount);
    }

    @PostMapping("/addInterest")
    public float addInterestAccount(@RequestParam String id, @RequestParam float percent){
        return accountService.addInterestAccount(id, percent);
    }
}
