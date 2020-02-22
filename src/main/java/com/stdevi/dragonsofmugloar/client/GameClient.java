package com.stdevi.dragonsofmugloar.client;

import com.stdevi.dragonsofmugloar.model.Game;
import com.stdevi.dragonsofmugloar.model.Reputation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rootURL}")
    private String rootURL;

    private Game game;

    public void startNewGame() {
        String url = String.format("%s/game/start", rootURL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        game = restTemplate.postForObject(url, headers, Game.class);
        System.out.println(game);
    }

    public void getReputation() {
        String url = String.format("%s/%s/investigate/reputation", rootURL, game.getGameId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Reputation reputation = restTemplate.postForObject(url, headers, Reputation.class);
        System.out.println(reputation);
    }
}
