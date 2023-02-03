package com.example.loggingtask.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    private int number;
    private String nameOfCustomer;
    public Order(String nameOfCustomer) {
        this.nameOfCustomer = nameOfCustomer;
    }

}