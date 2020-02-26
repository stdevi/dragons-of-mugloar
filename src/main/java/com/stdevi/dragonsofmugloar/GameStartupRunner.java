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
        logBestScore(IntStream.range(0, 4)
                .mapToObj(i -> gameController.completeGame())
                .max(Comparator.comparingInt(Game::getScore))
                .orElseGet(Game::new));
    }

    public void logBestScore(Game game) {
        log.warn("Your best score:" + game.getScore());
    }
}
