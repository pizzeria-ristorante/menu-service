package com.pizzeria.menuservice.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientItemDto {

    private String name;
    private int quantity;
}
