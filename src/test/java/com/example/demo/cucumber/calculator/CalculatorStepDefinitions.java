package com.example.demo.cucumber.calculator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorStepDefinitions {

    private Calculator calculator;
    private Integer result;

    @Given("I have a calculator")
    public void i_have_a_calculator() {
        this.calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void i_add_and(Integer int1, Integer int2) {
        this.result = this.calculator.add(int1, int2);
    }

    @Then("I should get {int}")
    public void i_should_get(Integer expected) {
        assertThat(this.result).isEqualTo(expected);
    }
}
