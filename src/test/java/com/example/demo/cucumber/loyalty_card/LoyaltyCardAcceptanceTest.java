package com.example.demo.cucumber.loyalty_card;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = "com.example.demo.cucumber.loyalty_card.steps",
        features = "src/test/resources/features/loyalty_card"
)
public class LoyaltyCardAcceptanceTest {
}
