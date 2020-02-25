package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.game.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@RestClientTest(GameService.class)
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    public void whenStartingNewGame_thenGameHasInitialParameters() {
        Game game = gameService.startNewGame();

        assertThat(game.getLives()).isEqualTo(3);
        assertThat(game.getGold()).isEqualTo(0);
        assertThat(game.getScore()).isEqualTo(0);
        assertThat(game.getScore()).isEqualTo(0);
    }
}