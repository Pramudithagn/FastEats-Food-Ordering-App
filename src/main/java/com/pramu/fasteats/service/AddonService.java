package com.pramu.fasteats.service;

import com.pramu.fasteats.model.AddonItem;
import com.pramu.fasteats.model.AddonCategory;

import java.util.List;

public interface AddonService {

    public AddonCategory findAddonCategoryById(Long id) throws Exception;

    public List<AddonCategory> findAddonCategoryByRestaurantId(Long id) throws Exception;

    public AddonCategory createAddonCategory(String name, Long restaurantId) throws Exception;

    public List<AddonItem> findRestaurantAddonItems(Long restaurantId) throws Exception;

    public AddonItem addonItemStockUpdate(Long id) throws Exception;

    public AddonItem createAddonItem(Long restaurantId, String name, Long categoryId) throws Exception;

}
