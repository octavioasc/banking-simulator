package com.bank.bankingsimulator.controller;

import com.bank.bankingsimulator.kafka.EventProducer;
import com.bank.bankingsimulator.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private EventProducer eventProducer;

    public EventController(EventProducer kafkaProducer) {
        this.eventProducer = kafkaProducer;
    }

    @PostMapping("/makeDeposit")
    public ResponseEntity<String> makeDepositAccount(@RequestParam Long id, @RequestParam float amount){
        Event event;
        try{
            event = new Event("makeDepositAccount", String.valueOf(amount));
            event.setAccount_id(id);
            // send to kafka broker.
            eventProducer.sendMessage(event);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception makeDepositAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + event);
    }

    @PostMapping("/getFromAccount")
    public ResponseEntity<String> getFromAccount(@RequestParam Long id, @RequestParam float amount){
        Event event;
        try{
            event = new Event("getFromAccount", String.valueOf(amount));
            event.setAccount_id(id);
            // send to kafka broker.
            eventProducer.sendMessage(event);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception getFromAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + event);
    }

    @PostMapping("/addInterest")
    public ResponseEntity<String> addInterestAccount(@RequestParam Long id, @RequestParam float percent){
        Event event;
        try{
            event = new Event("addInterestAccount", String.valueOf(percent));
            event.setAccount_id(id);
            // send to kafka broker.
            eventProducer.sendMessage(event);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json")
                    .body("Exception addInterestAccount: " + e);
        }
        return  ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
                .body("" + event);
    }
}
