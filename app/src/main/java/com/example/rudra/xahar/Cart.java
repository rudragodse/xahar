package com.example.rudra.xahar;

public class Cart {

    private String item_name;
    private String item_cost;

    public Cart() {

    }

    public Cart(String name, String item_cost) {
        this.item_name = name;
        this.item_cost = item_cost;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_cost() {
        return item_cost;
    }
}
