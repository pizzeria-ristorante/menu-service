package com.pizzeria.menuservice.domain;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PizzaPricingService {

    public double calculatePrice(Set<IngredientItem> ingredients) {
        double price = 0;
        for (IngredientItem ingredient : ingredients) {
            if ("Pizzateig".equalsIgnoreCase(ingredient.getName())) {
                price += 14.0;
            } else if ("Tomaten".equalsIgnoreCase(ingredient.getName()) || "Mozzarella".equalsIgnoreCase(ingredient.getName())) {
                price += ingredient.getQuantity() * 0.5;
            } else {
                price += ingredient.getQuantity() * 1.5;
            }
        }
        return price;
    }
}
