package com.pramu.fasteats.service;

import com.pramu.fasteats.model.AddonCategory;
import com.pramu.fasteats.model.AddonItem;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.repository.AddonCategoryRepository;
import com.pramu.fasteats.repository.AddonItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddonServiceImpl implements AddonService {

    @Autowired
    private AddonCategoryRepository addonCategoryRepository;

    @Autowired
    private AddonItemRepository addonItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public AddonCategory findAddonCategoryById(Long id) throws Exception {
        Optional<AddonCategory> addonCategory = addonCategoryRepository.findById(id);

        if(addonCategory.isEmpty()){
            throw new Exception("AddonCategory not found");
        }
        return addonCategory.get();
    }

    @Override
    public List<AddonCategory> findAddonCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id); //to check restaurant is present or not
        return addonCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public AddonCategory createAddonCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        AddonCategory addonCategory = new AddonCategory();
        addonCategory.setName(name);
        addonCategory.setRestaurant(restaurant);

        return addonCategoryRepository.save(addonCategory);
    }

    @Override
    public List<AddonItem> findRestaurantAddonItems(Long restaurantId) throws Exception {
        return addonItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public AddonItem addonItemStockUpdate(Long id) throws Exception {
        Optional<AddonItem> optAddonItem = addonItemRepository.findById(id);

        if(optAddonItem.isEmpty()){
            throw new Exception("AddonItem not found");
        }

        AddonItem addonItem = optAddonItem.get();
        addonItem.setInStock(!addonItem.isInStock());
        return addonItemRepository.save(addonItem);
    }

    @Override
    public AddonItem createAddonItem(Long restaurantId, String name, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        AddonCategory addonCategory = findAddonCategoryById(categoryId);
        AddonItem addonItem = new AddonItem();
        addonItem.setRestaurant(restaurant);
        addonItem.setName(name);
        addonItem.setAddonCategory(addonCategory);

        AddonItem savedAddonItem = addonItemRepository.save(addonItem);
        addonCategory.getAddons().add(savedAddonItem);

        return savedAddonItem;
    }
}
