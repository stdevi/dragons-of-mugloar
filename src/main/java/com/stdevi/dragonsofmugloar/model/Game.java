package com.stdevi.dragonsofmugloar.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Game {
    private String gameId;
    private int lives;
    private int gold;
    private int level;
    private int score;
    private int highScore;
    private int turn;
}
