package com.example.demo.cucumber.loyalty_card;

public class Customer {

    private String name;
    private int pointsEarned;
    private DrinkCatalog catalog;

    public Customer(String name, DrinkCatalog catalog) {
        this.name = name;
        this.catalog = catalog;
    }

    public void orders(String drink, Integer quantity) {
        pointsEarned += catalog.getPointsForCategory(drink) * quantity;
    }

    public Integer getPoints() {
        return pointsEarned;
    }
}
