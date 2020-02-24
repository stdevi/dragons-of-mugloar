package com.stdevi.dragonsofmugloar;

import com.stdevi.dragonsofmugloar.controller.GameController;
import com.stdevi.dragonsofmugloar.model.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.stream.IntStream;

@Component
@Slf4j
public class GameStartupRunner {

    @Autowired
    private GameController gameController;

    @PostConstruct
    public void init() {
        logGame(IntStream.range(0, 2)
                .mapToObj(i -> gameController.completeGame())
                .max(Comparator.comparingInt(Game::getScore))
                .orElseGet(Game::new));
    }

    public void logGame(Game game) {
        log.warn("\uD83D\uDC51Your best game\uD83D\uDC51" +
                "\nHighest score: " + game.getHighScore() +
                "\nYour final score: " + game.getScore() +
                "\nYou final level: " + game.getLevel() +
                "\nNumber of turns: " + game.getTurn());
    }
}
