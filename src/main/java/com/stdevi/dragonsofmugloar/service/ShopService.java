package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.shop.Item;
import com.stdevi.dragonsofmugloar.model.shop.Purchase;
import com.stdevi.dragonsofmugloar.model.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ShopService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${shop.getItemsUrl}")
    private String getItemsUrl;

    @Value("${shop.purchaseItemUrl}")
    private String purchaseItemUrl;

    public Shop getShop(String gameId) {
        String url = String.format(getItemsUrl, gameId);

        try {
            Item[] items = restTemplate.getForObject(url, Item[].class);
            return items != null ? new Shop(Arrays.asList(items)) : null;
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Purchase purchaseItem(String gameId, String itemId) {
        String url = String.format(purchaseItemUrl, gameId, itemId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            return restTemplate.postForObject(url, headers, Purchase.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
}
