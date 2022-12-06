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
    private String status;

    public Account(){

    }

    public Account(Long id, float balance, String status) {
        this.id = id;
        this.balance = balance;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
