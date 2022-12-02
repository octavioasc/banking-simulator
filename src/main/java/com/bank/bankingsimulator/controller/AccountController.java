package com.bank.bankingsimulator.controller;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> getBalanceAccount(@PathVariable(name = "id") String id){
        float result;
        try{
            result = accountService.getBalanceAcount(id);
        }catch (Exception e){
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception getBalanceAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }

    @PostMapping("/makeDeposit")
    public ResponseEntity<String> makeDepositAccount(@RequestParam String id, @RequestParam float amount){
        float result;
        try{
            result = accountService.makeDepositAccount(id,amount);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception makeDepositAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }

    @PostMapping("/getFromAccount")
    public ResponseEntity<String> getFromAccount(@RequestParam String id, @RequestParam float amount){
        float result;
        try{
            result = accountService.getFromAccount(id, amount);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception getFromAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }

    @PostMapping("/addInterest")
    public ResponseEntity<String> addInterestAccount(@RequestParam String id, @RequestParam float percent){
        float result;
        try{
            result = accountService.addInterestAccount(id, percent);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception addInterestAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }
}
