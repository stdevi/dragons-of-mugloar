package com.stdevi.dragonsofmugloar;

import com.stdevi.dragonsofmugloar.model.Game;
import com.stdevi.dragonsofmugloar.services.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameStartupRunner implements CommandLineRunner {

    private final GameService gameService;

    public GameStartupRunner(GameService client) {
        this.gameService = client;
    }

    @Override
    public void run(String... args) throws Exception {
        Game game = gameService.startNewGame();
    }
}
