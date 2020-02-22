package com.stdevi.dragonsofmugloar;

import com.stdevi.dragonsofmugloar.client.GameClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameStartupRunner implements CommandLineRunner {

    private final GameClient client;

    public GameStartupRunner(GameClient client) {
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        client.startNewGame();
    }
}