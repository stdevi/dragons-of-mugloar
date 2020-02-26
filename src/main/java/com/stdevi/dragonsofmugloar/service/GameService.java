package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${game.startNewGameUrl}")
    private String startNewGameUrl;

    public Game startNewGame() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            return restTemplate.postForObject(startNewGameUrl, headers, Game.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
}
