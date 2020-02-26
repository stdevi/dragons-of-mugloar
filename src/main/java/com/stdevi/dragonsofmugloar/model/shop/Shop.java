package com.stdevi.dragonsofmugloar.model.shop;

import lombok.Data;

import java.util.List;

@Data
public class Shop {
    private List<Item> items;

    public Shop(List<Item> items) {
        this.items = items;
    }
}
