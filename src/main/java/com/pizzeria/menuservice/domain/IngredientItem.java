package com.pizzeria.menuservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientItem {

    private String name;
    private int quantity;
}
