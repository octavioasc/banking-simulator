package com.bank.bankingsimulator.controller;


import com.bank.bankingsimulator.service.BankingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {
    private BankingService bankingService;

    public TransactionsController(BankingService bankingService){
        this.bankingService = bankingService;
    }

    @GetMapping("/getBalance/{id}")
    public ResponseEntity<String> getBalance(@PathVariable(name = "id") Long id){
        float balance;
        try{
            balance = bankingService.getBalance(id);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception getBalanceAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + balance);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam Long id, @RequestParam float amount){
        float result;
        try{
            result = bankingService.deposit(id,amount);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception makeDepositAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam Long id, @RequestParam float amount){
        float result;
        try{
            result = bankingService.withdraw(id, amount);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception wi: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }

    @PostMapping("/addInterest")
    public ResponseEntity<String> addInterest(@RequestParam Long id, @RequestParam float percent){
        float result;
        try{
            result = bankingService.addInterest(id, percent);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception addInterestAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + result);
    }

}
