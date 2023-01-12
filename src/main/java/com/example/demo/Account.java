package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Account {
    @Id
    private Long aid;
    private Long bid;
    private Long abalance;
    private String filler;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Long getAbalance() {
        return abalance;
    }

    public void setAbalance(Long abalance) {
        this.abalance = abalance;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", bid=" + bid +
                ", abalance=" + abalance +
                ", filler='" + filler + '\'' +
                '}';
    }
}
