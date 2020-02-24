package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.ad.Ad;
import com.stdevi.dragonsofmugloar.model.ad.AdResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AdService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ad.getAdsUrl}")
    private String getAdsUrl;

    @Value("${ad.solveAdUrl}")
    private String solveAdUrl;

    public List<Ad> getAds(String gameId) {
        String url = String.format(getAdsUrl, gameId);

        try {
            Ad[] ads = restTemplate.getForObject(url, Ad[].class);
            return ads != null ? Arrays.asList(ads) : new ArrayList<>();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public AdResult solveAd(String gameId, String adId) {
        String url = String.format(solveAdUrl, gameId, adId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            return restTemplate.postForObject(url, headers, AdResult.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
}
