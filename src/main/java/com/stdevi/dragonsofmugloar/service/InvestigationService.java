package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.game.Reputation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class InvestigationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${investigation.reputationUrl}")
    private String reputationUrl;

    public Reputation getReputation(String gameId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            String url = String.format(reputationUrl, gameId);
            return restTemplate.postForObject(url, headers, Reputation.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
}
