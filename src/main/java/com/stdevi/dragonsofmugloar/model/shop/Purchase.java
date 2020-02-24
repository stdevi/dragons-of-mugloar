package com.stdevi.dragonsofmugloar.model.shop;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Purchase {
    private String shoppingSuccess;
    private int gold;
    private int lives;
    private int level;
    private int turn;
}
