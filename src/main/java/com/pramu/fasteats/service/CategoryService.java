package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Category;

import java.util.List;

public interface CategoryService {

    public Category findCategoryById(Long id) throws Exception;

    public List<Category> findCategoryByRestaurant(Long userId) throws Exception;

    public Category createCategory(String name, Long userId) throws Exception;
}
