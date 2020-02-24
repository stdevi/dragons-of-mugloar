package com.stdevi.dragonsofmugloar.model.game;

import lombok.Data;
import lombok.NoArgsConstructor;
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

    public Game() {
    }

    public Game(Game game) {
        this.gameId = game.gameId;
        this.lives = game.lives;
        this.gold = game.gold;
        this.level = game.level;
        this.score = game.score;
        this.highScore = game.highScore;
        this.turn = game.turn;
    }
}
