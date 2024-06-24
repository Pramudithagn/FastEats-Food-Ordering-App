package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.IngredientCategory;
import com.pramu.fasteats.model.IngredientItem;
import com.pramu.fasteats.request.IngredientCategoryRequest;
import com.pramu.fasteats.request.IngredientItemRequest;
import com.pramu.fasteats.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/ingredientCategory")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest request) throws Exception {

        IngredientCategory ingredientCategory = ingredientService.createIngredientCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }

    @PostMapping("/ingredientItem")
    public ResponseEntity<IngredientItem> createIngredientItem(
            @RequestBody IngredientItemRequest request) throws Exception {

        IngredientItem ingredientItem = ingredientService.createIngredientItem(request.getRestaurantId(), request.getName(), request.getIngredientCategoryId());
        return new ResponseEntity<>(ingredientItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/updatestock")
    public ResponseEntity<IngredientItem> updateIngredientStock(
            @PathVariable Long id) throws Exception {

        IngredientItem ingredientItem = ingredientService.ingredientItemStockUpdate(id);
        return new ResponseEntity<>(ingredientItem, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/items")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredientItems(
            @PathVariable Long id) throws Exception {

        List<IngredientItem> ingredientItems = ingredientService.findRestaurantIngredientItems(id);
        return new ResponseEntity<>(ingredientItems, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/categories")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategories(
            @PathVariable Long id) throws Exception {

        List<IngredientCategory> ingredientcategories = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredientcategories, HttpStatus.OK);
    }
}
