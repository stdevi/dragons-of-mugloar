package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.game.Game;
import com.stdevi.dragonsofmugloar.model.game.Reputation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@RestClientTest({InvestigationService.class, GameService.class})
public class InvestigationServiceTest {

    @Autowired
    private GameService gameService;
    @Autowired
    private InvestigationService investigationService;

    private Game game;

    @Before
    public void setUp() {
        game = gameService.startNewGame();
    }

    @Test
    public void whenStartingNewGame_thenReputationHasInitParameters() {
        Reputation reputation = investigationService.getReputation(game.getGameId());

        assertThat(reputation.getPeople()).isEqualTo(0);
        assertThat(reputation.getState()).isEqualTo(0);
        assertThat(reputation.getUnderworld()).isEqualTo(0);
    }
}