package com.bank.bankingsimulator.model;
import jakarta.persistence.*;

@Entity
@Table
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String value;
    private long account_id;

    public Event() {
    }

    public Event(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Event(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + this.name + '\'' +
                ", value='" + this.value + '\'' +
                '}';
    }
}
