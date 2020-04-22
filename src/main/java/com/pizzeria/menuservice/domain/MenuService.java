package com.pizzeria.menuservice.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final PizzaPricingService pizzaPricingService;

    private Map<Integer, MenuEntry> menu_card = new HashMap<>();

    @PostConstruct
    private void postConstruct() {
        addMenuEntry("Salami", new IngredientItem("Salami", 2));
        addMenuEntry("Funghi", new IngredientItem("Funghi", 2));
        addMenuEntry("Funghi Porcini", new IngredientItem("Funghi Porcini", 3));
        addMenuEntry("Gorgonzola", new IngredientItem("Gorgonzola", 2));
        addMenuEntry("Napoli", new IngredientItem("Sardellen", 3));
        addMenuEntry("Margherita");
        addMenuEntry("Quattro Staggioni", new IngredientItem("Salami", 1), new IngredientItem("Prosciutto", 1), new IngredientItem("Funghi", 1), new IngredientItem("Ananas", 1));
    }

    public Iterable<MenuEntry> getAllMenuEntries() {
        return menu_card.values();
    }

    public Optional<MenuEntry> getMenuEntryById(int id) {
        return Optional.ofNullable(menu_card.get(id));
    }

    private void addMenuEntry(String name, IngredientItem... items) {
        List<IngredientItem> base = Arrays.asList(new IngredientItem("Pizzateig", 1), new IngredientItem("Pomodoro", 2), new IngredientItem("Mozzarella", 1));
        Set<IngredientItem> ingredientItems = new HashSet<>(Arrays.asList(items));
        ingredientItems.addAll(base);
        int currentId = menu_card.keySet().stream().max(Integer::compare).orElse(0);
        MenuEntry menuEntry = new MenuEntry(currentId + 1, name, ingredientItems, pizzaPricingService.calculatePrice(ingredientItems));
        menu_card.put(menuEntry.getId(), menuEntry);
    }
}
