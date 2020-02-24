package com.stdevi.dragonsofmugloar.controller;

import com.stdevi.dragonsofmugloar.model.shop.Item;
import com.stdevi.dragonsofmugloar.model.shop.Purchase;
import com.stdevi.dragonsofmugloar.service.ShopService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Data
@Component
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private Environment env;

    public Purchase buyRandomItem(String gameId, int gold) {
        List<Item> availableItems = getAvailableItems(gameId, gold);
        String randomItemId = availableItems.get(new Random().nextInt(availableItems.size())).getId();
        return purchaseItem(gameId, randomItemId);
    }

    public Purchase buyHealingPotion(String gameId) {
        String healingPotionId = env.getProperty("shop.items.healingPotionId");
        return purchaseItem(gameId, healingPotionId);
    }

    public List<Item> getAvailableItems(String gameId, int goldLimit) {
        return shopService.getShop(gameId).getItems().stream()
                .filter(item -> item.getCost() <= goldLimit)
                .collect(Collectors.toList());
    }

    private Purchase purchaseItem(String gameId, String itemId) {
        return shopService.purchaseItem(gameId, itemId);
    }
}
