package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.game.Game;
import com.stdevi.dragonsofmugloar.model.shop.Item;
import com.stdevi.dragonsofmugloar.model.shop.Shop;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@RestClientTest({ShopService.class, GameService.class})
public class ShopServiceTest {

    @Autowired
    private GameService gameService;
    @Autowired
    private ShopService shopService;

    private Game game;

    @Before
    public void setUp() {
        game = gameService.startNewGame();
    }

    @Test
    public void whenStartingNewGame_thenShopIsNotNull() {
        Shop shop = shopService.getShop(game.getGameId());

        assertThat(shop.getItems()).isNotEmpty();
    }

    @Test
    public void whenStartingNewGame_thenFirstItemHasNotEmptyParameters() {
        Shop shop = shopService.getShop(game.getGameId());
        Item item = shop.getItems().get(0);

        assertThat(item.getId()).isNotBlank();
        assertThat(item.getName()).isNotBlank();
        assertThat(item.getCost()).isNotNegative();
    }
}