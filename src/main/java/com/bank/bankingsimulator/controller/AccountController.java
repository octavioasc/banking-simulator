package com.bank.bankingsimulator.controller;

import com.bank.bankingsimulator.model.Account;
import com.bank.bankingsimulator.service.AccountProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    private final AccountProducerService accountProducerService;

    public AccountController(AccountProducerService accountProducerService) {
        this.accountProducerService = accountProducerService;
    }


    @PostMapping("/accounts")
    public void saveAccount(@RequestBody Account account){
        accountProducerService.saveAccount(account);
    }

    @GetMapping("/getBalance/{id}")
    public ResponseEntity<String> getBalanceAccount(@PathVariable(name = "id") Long id){
        float result;
        try{
            result = accountProducerService.getBalanceAcount(id);
        }catch (Exception e){
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception getBalanceAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }

    @PostMapping("/makeDeposit")
    public ResponseEntity<String> makeDepositAccount(@RequestParam Long id, @RequestParam float amount){
        float result;
        try{
            result = accountProducerService.makeDepositAccount(id,amount);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception makeDepositAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }

    @PostMapping("/getFromAccount")
    public ResponseEntity<String> getFromAccount(@RequestParam Long id, @RequestParam float amount){
        float result;
        try{
            result = accountProducerService.getFromAccount(id, amount);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception getFromAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }

    @PostMapping("/addInterest")
    public ResponseEntity<String> addInterestAccount(@RequestParam Long id, @RequestParam float percent){
        float result;
        try{
            result = accountProducerService.addInterestAccount(id, percent);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception addInterestAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }
}
