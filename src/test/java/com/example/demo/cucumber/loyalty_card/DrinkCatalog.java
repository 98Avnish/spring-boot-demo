package com.example.demo.cucumber.loyalty_card;

import java.util.HashMap;
import java.util.Map;

public class DrinkCatalog {

    private Map<String, String> catalog = new HashMap<>();
    private Map<String, Integer> pointsPerCategory = new HashMap<>();

    public void addDrink(String drink, String category) {
        catalog.put(drink, category);
    }

    public void setPointsPerCategory(String category, Integer points) {
        pointsPerCategory.put(category, points);
    }

    public int getPointsForCategory(String drink) {
        String category = catalog.get(drink);
        return pointsPerCategory.get(category);
    }
}
