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
    @ManyToOne
    @JoinColumn(name = "a_id")
    private Account a;

    public Event() {
    }

    public Event(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Event(String name) {
        this.name = name;
    }

    public Account getA() {
        return a;
    }

    public void setA(Account a) {
        this.a = a;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
