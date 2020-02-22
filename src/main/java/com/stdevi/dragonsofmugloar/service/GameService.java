package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.game.Game;
import com.stdevi.dragonsofmugloar.model.game.Reputation;
import com.stdevi.dragonsofmugloar.model.message.Ad;
import com.stdevi.dragonsofmugloar.model.message.AdResult;
import com.stdevi.dragonsofmugloar.model.shop.Item;
import com.stdevi.dragonsofmugloar.model.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rootURL}")
    private String rootURL;

    public Game startNewGame() {
        String url = String.format("%s/game/start", rootURL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Game> response = restTemplate.postForEntity(url, headers, Game.class);

        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
    }

    public Reputation getReputation(String gameId) {
        String url = String.format("%s/%s/investigate/reputation", rootURL, gameId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Reputation> response = restTemplate.postForEntity(url, headers, Reputation.class);

        return response.getStatusCode().equals(HttpStatus.OK) ? response.getBody() : null;
    }

    public List<Ad> getTasks(String gameId) {
        String url = String.format("%s/%s/messages", rootURL, gameId);
        ResponseEntity<Ad[]> response = restTemplate.getForEntity(url, Ad[].class);

        return response.getStatusCode().equals(HttpStatus.OK) &&
                response.getBody() != null ? new ArrayList<>(Arrays.asList(response.getBody())) : null;
    }

    public AdResult solveTask(String gameId, String taskId) {
        String url = String.format("%s/%s/solve/%s", rootURL, gameId, taskId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<AdResult> response = restTemplate.postForEntity(url, headers, AdResult.class);

        return response.getStatusCode().equals(HttpStatus.OK) ? response.getBody() : null;
    }

    public Shop getShop(String gameId) {
        String url = String.format("%s/%s/shop", rootURL, gameId);
        ResponseEntity<Item[]> response = restTemplate.getForEntity(url, Item[].class);

        return response.getStatusCode().equals(HttpStatus.OK) &&
                response.getBody() != null ? new Shop(Arrays.asList(response.getBody())) : null;
    }
}
