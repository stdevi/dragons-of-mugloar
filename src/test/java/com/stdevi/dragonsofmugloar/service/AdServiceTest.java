package com.stdevi.dragonsofmugloar.service;

import com.stdevi.dragonsofmugloar.model.ad.Ad;
import com.stdevi.dragonsofmugloar.model.ad.AdResult;
import com.stdevi.dragonsofmugloar.model.game.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@RestClientTest({AdService.class, GameService.class})
public class AdServiceTest {

    @Autowired
    private GameService gameService;
    @Autowired
    private AdService adService;

    private Game game;

    @Before
    public void setUp() {
        game = gameService.startNewGame();
    }

    @Test
    public void whenGettingAds_thenAdListIsNotEmpty() {
        List<Ad> ads = adService.getAds(game.getGameId());

        assertThat(ads).isNotEmpty();
    }

    @Test
    public void whenGettingAds_thenFirstAdHasNotNullParameters() {
        List<Ad> ads = adService.getAds(game.getGameId());
        Ad ad = ads.get(0);

        assertThat(ad.getAdId()).isNotBlank();
        assertThat(ad.getProbability()).isNotBlank();
        assertThat(ad.getMessage()).isNotBlank();
        assertThat(ad.getReward()).isNotBlank();
    }

    @Test
    public void whenSolveAd_thenAdResultIsNotNull() {
        List<Ad> ads = adService.getAds(game.getGameId());
        AdResult adResult = adService.solveAd(game.getGameId(), ads.get(0).getAdId());

        assertThat(adResult).isNotNull();
    }

    @Test
    public void whenSolveAd_thenAdResultTurnIs1() {
        List<Ad> ads = adService.getAds(game.getGameId());
        AdResult adResult = adService.solveAd(game.getGameId(), ads.get(0).getAdId());


        assertThat(adResult.getMessage()).isNotBlank();
        if (adResult.isSuccess()) {
            assertThat(adResult.getTurn()).isEqualTo(1);
        }
    }
}