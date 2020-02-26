package com.stdevi.dragonsofmugloar.model.ad;

import lombok.Data;

@Data
public class AdResult {
    private boolean success;
    private int lives;
    private int gold;
    private int score;
    private int highScore;
    private int turn;
    private String message;
}