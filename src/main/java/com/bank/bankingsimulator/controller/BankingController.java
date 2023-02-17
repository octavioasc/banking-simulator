package com.bank.bankingsimulator.controller;

import com.bank.bankingsimulator.kafka.EventProducer;
import com.bank.bankingsimulator.model.Event;
import com.bank.bankingsimulator.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BankingController {
    private EventProducer eventProducer;
    private EventService eventService;
    public BankingController(EventProducer kafkaProducer, EventService eventService) {
        this.eventProducer = kafkaProducer;
        this.eventService = eventService;
    }

    @GetMapping("/getBalance/{id}")
    public ResponseEntity<String> getBalance(@PathVariable(name = "id") Long id){
        float balance;
        try{
            balance = eventService.getBalanceFromEvents(id);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception getBalance: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + balance);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam Long id, @RequestParam float amount){
        Event event;
        try{
            event = new Event("deposit", String.valueOf(amount));
            event.setAccount_id(id);
            // send to kafka broker.
            eventProducer.newEvent(event);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception deposit: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + event);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam Long id, @RequestParam float amount){
        Event event;
        try{
            event = new Event("withdraw", String.valueOf(amount));
            event.setAccount_id(id);
            // send to kafka broker.
            eventProducer.newEvent(event);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception withdraw: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + event);
    }

    @PostMapping("/addInterest")
    public ResponseEntity<String> addInterest(@RequestParam Long id, @RequestParam float percent){
        Event event;
        try{
            event = new Event("addInterest", String.valueOf(percent));
            event.setAccount_id(id);
            // send to kafka broker.
            eventProducer.newEvent(event);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception addInterest: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + event);
    }
}
