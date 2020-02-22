package com.stdevi.dragonsofmugloar.services;

import com.stdevi.dragonsofmugloar.model.Game;
import com.stdevi.dragonsofmugloar.model.Reputation;
import com.stdevi.dragonsofmugloar.model.Task;
import com.stdevi.dragonsofmugloar.model.TaskResult;
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

    public List<Task> getTasks(String gameId) {
        String url = String.format("%s/%s/messages", rootURL, gameId);
        ResponseEntity<Task[]> response = restTemplate.getForEntity(url, Task[].class);

        return response.getStatusCode().equals(HttpStatus.OK) &&
                response.getBody() != null ? new ArrayList<>(Arrays.asList(response.getBody())) : null;
    }

    public TaskResult solveTask(String gameId, String taskId) {
        String url = String.format("%s/solve/%s", gameId, taskId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<TaskResult> response = restTemplate.postForEntity(url, headers, TaskResult.class);

        return response.getStatusCode().equals(HttpStatus.OK) ? response.getBody() : null;
    }
}
