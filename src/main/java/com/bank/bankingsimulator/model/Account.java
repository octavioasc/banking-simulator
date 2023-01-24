package com.bank.bankingsimulator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

@Entity
@Table
public class Account {
    @jakarta.persistence.Id
    @GeneratedValue
    private Long id;
    private float balance;

    public Account(){

    }

    public Account(float balance, String status) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}
