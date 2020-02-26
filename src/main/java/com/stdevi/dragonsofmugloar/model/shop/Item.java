package com.stdevi.dragonsofmugloar.model.shop;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Item {
    private String id;
    private String name;
    private int cost;
}
