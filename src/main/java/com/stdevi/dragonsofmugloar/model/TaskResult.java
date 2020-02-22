package com.stdevi.dragonsofmugloar.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaskResult {
    private boolean success;
    private int lives;
    private int gold;
    private int score;
    private int highScore;
    private int turn;
    private String message;
}