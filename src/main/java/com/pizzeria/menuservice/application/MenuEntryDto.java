package com.pizzeria.menuservice.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntryDto {

    private Integer id;
    private String name;
    private Set<IngredientItemDto> ingredients;
    private double price;

}
