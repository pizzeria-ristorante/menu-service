package com.pizzeria.menuservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class MenuEntry {

    private Integer id;
    private String name;
    private Set<IngredientItem> ingredients;
    private double price;
}
