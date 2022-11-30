package com.bank.bankingsimulator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Accounts")
public class Account {
    @Id
    private String id;
    private float balance;
    private String status;

    public Account(){

    }

    public Account(String id, float balance, String status) {
        this.id = id;
        this.balance = balance;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
