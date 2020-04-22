package com.pizzeria.menuservice.application;

import com.pizzeria.menuservice.domain.IngredientItem;
import com.pizzeria.menuservice.domain.MenuEntry;
import com.pizzeria.menuservice.domain.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/menu-card-entries")
@Slf4j
public class MenuServiceRestController {

    private final MenuService menuService;

    @GetMapping
    public Set<MenuEntryDto> getAllMenuEntries(@RequestParam Optional<String> name) {
        log.info("Getting all menu entries.");
        Iterable<MenuEntry> menuEntries = menuService.getAllMenuEntries();
        Set<MenuEntry> allMenuEntries = new HashSet<>();
        menuEntries.forEach(allMenuEntries::add);
        log.info("Found total of {} menu entries and for name: {}", allMenuEntries.size(), name);
        return allMenuEntries
                .stream()
                .filter(menuItem -> name.isPresent() ? menuItem.getName().equals(name.get()) : true)
                .map(this::mapToDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuEntryDto> getbyId(@PathVariable("id") int id) {
        log.info("Getting menu entry with id: {}", id);
        return menuService.getMenuEntryById(id)
                .map(menuItem -> ResponseEntity.ok().body(this.mapToDto(menuItem)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private MenuEntryDto mapToDto(MenuEntry menuEntry) {
        return new MenuEntryDto(menuEntry.getId(), menuEntry.getName(), menuEntry.getIngredients().stream().map(this::mapToDto).collect(Collectors.toSet()), menuEntry.getPrice());
    }

    private IngredientItemDto mapToDto(IngredientItem ingredient) {
        return new IngredientItemDto(ingredient.getName(), ingredient.getQuantity());
    }
}
