package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.AddonCategory;
import com.pramu.fasteats.model.AddonItem;
import com.pramu.fasteats.request.AddonCategoryRequest;
import com.pramu.fasteats.request.AddonItemRequest;
import com.pramu.fasteats.service.AddonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/addon")
public class AddonController {

    @Autowired
    private AddonService addonService;

    @PostMapping("/category")
    public ResponseEntity<AddonCategory> createAddonCategory(
            @RequestBody AddonCategoryRequest request) throws Exception {
        AddonCategory addonCategory = addonService.createAddonCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(addonCategory, HttpStatus.CREATED);
    }
    @PostMapping()
    public ResponseEntity<AddonItem> createAddonItem(
            @RequestBody AddonItemRequest request) throws Exception {
        AddonItem addonItem = addonService.createAddonItem(request.getRestaurantId(), request.getName(), request.getAddonCategoryId());
        return new ResponseEntity<>(addonItem, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/updatestock")
    public ResponseEntity<AddonItem> updateAddonStock(
            @PathVariable Long id) throws Exception {
        AddonItem addonItem = addonService.addonItemStockUpdate(id);
        return new ResponseEntity<>(addonItem, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}/items")
    public ResponseEntity<List<AddonItem>> getRestaurantAddonItems(
            @PathVariable Long id) throws Exception {
        List<AddonItem> addonItems = addonService.findRestaurantAddonItems(id);
        return new ResponseEntity<>(addonItems, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}/categories")
    public ResponseEntity<List<AddonCategory>> getRestaurantAddonCategories(
            @PathVariable Long id) throws Exception {
        List<AddonCategory> addoncategories = addonService.findAddonCategoryByRestaurantId(id);
        return new ResponseEntity<>(addoncategories, HttpStatus.OK);
    }
}
