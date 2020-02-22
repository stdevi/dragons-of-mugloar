package com.stdevi.dragonsofmugloar.client;

import com.stdevi.dragonsofmugloar.model.Game;
import com.stdevi.dragonsofmugloar.model.Message;
import com.stdevi.dragonsofmugloar.model.Reputation;
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
public class GameClient {

    private final RestTemplate restTemplate;

    @Value("${rootURL}")
    private String rootURL;

    public GameClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Game startNewGame() {
        String url = String.format("%s/game/start", rootURL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Game> response = restTemplate.postForEntity(url, headers, Game.class);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        }

        return null;
    }

    public Reputation getReputation(String gameId) {
        String url = String.format("%s/%s/investigate/reputation", rootURL, gameId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Reputation> response = restTemplate.postForEntity(url, headers, Reputation.class);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        }

        return null;
    }

    public List<Message> getMessages(String gameId) {
        String url = String.format("%s/%s/messages", rootURL, gameId);
        ResponseEntity<Message[]> response = restTemplate.getForEntity(url, Message[].class);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return new ArrayList<>(Arrays.asList(response.getBody()));
        }

        return null;
    }
}
