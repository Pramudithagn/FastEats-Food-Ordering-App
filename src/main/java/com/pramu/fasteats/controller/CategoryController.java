package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.Category;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.service.CategoryService;
import com.pramu.fasteats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserByJwtToken(token);
        Category createdCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantCategory(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(token);
        List<Category> categories = categoryService.findCategoryByRestaurant(id);

        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
}
