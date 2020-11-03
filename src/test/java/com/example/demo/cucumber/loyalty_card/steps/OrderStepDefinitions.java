package com.example.demo.cucumber.loyalty_card.steps;

import com.example.demo.cucumber.loyalty_card.Customer;
import com.example.demo.cucumber.loyalty_card.DrinkCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStepDefinitions {

    private DrinkCatalog drinkCatalog = new DrinkCatalog();
    private Customer customer;

    @Given("the following drink categories:")
    public void theFollowingDrinkCategories(List<Map<String, String>> drinkCategories) {
        drinkCategories.stream()
                .forEach(drinkCategory -> {
                    String drink = drinkCategory.get("Drink");
                    String category = drinkCategory.get("Category");
                    Integer points = Integer.parseInt(drinkCategory.get("Points"));

                    drinkCatalog.addDrink(drink, category);
                    drinkCatalog.setPointsPerCategory(category, points);
                });
    }

    @Given("{word} is a Customer")
    public void michealIsACustomer(String name) {
        customer = new Customer(name, drinkCatalog);
    }

    @When("^(\\w+) purchase (\\d+) (\\w+) drink$")
    public void michealPurchaseQuantityDrinkDrink(String name, Integer quantity, String drink) {
        customer.orders(drink, quantity);
    }

    @Then("^he should earn (\\d+) points$")
    public void heShouldEarnPointsPoints(Integer points) {
        assertThat(customer.getPoints()).isEqualTo(points);
    }

}
