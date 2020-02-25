package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.game.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.SimpleRequestExpectationManager;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
@RestClientTest(GameService.class)
public class GameServiceTest {

    @Autowired
    private GameService gameService;
    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private Environment env;

    private SimpleRequestExpectationManager manager = new SimpleRequestExpectationManager();

    @Before
    public void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void whenStartingNewGame_thenClientMakesCorrectCall() {
        server.expect(requestTo(env.getRequiredProperty("game.startNewGameUrl"))).andRespond(withSuccess());
        Game game = gameService.startNewGame();

        this.manager.verify();
        assertThat(game.getLives()).isEqualTo(3);
        assertThat(game.getGold()).isEqualTo(0);
        assertThat(game.getScore()).isEqualTo(0);
        assertThat(game.getScore()).isEqualTo(0);
    }
}