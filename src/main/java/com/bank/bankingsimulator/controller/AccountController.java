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
    public void save(@RequestBody Account account){
        accountService.save(account);
    }

    @GetMapping("/getBalance/{id}")
    public float getBalance(@PathVariable(name = "id") String id){
        return  accountService.getBalance(id);
    }

    @PostMapping("/makeDeposit")
    public float makeDeposit(@RequestParam String id, @RequestParam float amount){
        return accountService.makeDeposit(id, amount);
    }
}
