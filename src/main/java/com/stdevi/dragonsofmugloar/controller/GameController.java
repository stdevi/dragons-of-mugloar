package com.stdevi.dragonsofmugloar.controller;

import com.stdevi.dragonsofmugloar.model.ad.Ad;
import com.stdevi.dragonsofmugloar.model.ad.AdResult;
import com.stdevi.dragonsofmugloar.model.game.Game;
import com.stdevi.dragonsofmugloar.model.shop.Purchase;
import com.stdevi.dragonsofmugloar.service.GameService;
import com.stdevi.dragonsofmugloar.service.InvestigationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Component
@Data
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private InvestigationService investigationService;
    @Autowired
    private AdController adController;
    @Autowired
    private ShopController shopController;

    private Game game;
    private List<Ad> currentAds;

    public Game completeGame() {
        initGame();
        playGame();
        return new Game(game);
    }

    private void initGame() {
        game = gameService.startNewGame();
        currentAds = adController.retrieveAds(game.getGameId());
    }

    public void playGame() {
        while (game.getLives() > 0) {
            heal();
            levelUp();
            solveAd();
        }
    }

    private void heal() {
        if (game.getLives() == 1 && game.getGold() >= 50) {
            Purchase purchase = shopController.buyHealingPotion(game.getGameId());
            updateGame(purchase);
            updateCurrentAds();
        }
    }

    private void levelUp() {
        while (isLevelUpNeeded() && isLevelUpPossible()) {
            Purchase purchase = shopController.buyRandomItem(game.getGameId(), game.getGold());
            updateGame(purchase);
            updateCurrentAds();
        }
    }

    private void solveAd() {
        AdResult adResult = adController.solveOptimalAd(game, currentAds);
        updateGame(adResult);
        updateCurrentAds();
    }

    private void updateGame(Purchase purchase) {
        game.setGold(purchase.getGold());
        game.setLives(purchase.getLives());
        game.setLevel(purchase.getLevel());
        game.setTurn(purchase.getTurn());
    }

    private void updateGame(AdResult adResult) {
        game.setGold(adResult.getGold());
        game.setLives(adResult.getLives());
        game.setScore(adResult.getScore());
        game.setTurn(adResult.getTurn());
    }

    public void updateCurrentAds() {
        if (game.getLives() > 0) {
            currentAds = adController.retrieveAds(game.getGameId());
        }
    }

//    private void updateReputation() {
//        Reputation reputation = investigationService.getReputation(gameState.getGameId());
//        gameState.setReputation(reputation);
//        updateCurrentAds(adController.retrieveAds(gameState.getGameId()));
//    }

    private boolean isLevelUpNeeded() {
        return currentAds.get(0).getLevelOfDifficulty() >= 3;
    }

    private boolean isLevelUpPossible() {
        return game.getGold() >= 100 && game.getLives() > 1;
    }
}
