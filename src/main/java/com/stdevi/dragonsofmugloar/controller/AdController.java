package com.stdevi.dragonsofmugloar.controller;

import com.stdevi.dragonsofmugloar.model.ad.Ad;
import com.stdevi.dragonsofmugloar.model.ad.AdResult;
import com.stdevi.dragonsofmugloar.model.game.Game;
import com.stdevi.dragonsofmugloar.service.AdService;
import com.stdevi.dragonsofmugloar.utils.AdDecoder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class AdController {

    @Autowired
    private AdService adService;

    private static double computeAdScore(Ad ad) {
        // Trade-off between ad probability and expires time
        return (double) ad.getLevelOfDifficulty() / 2 + ad.getExpiresIn();
    }

    public AdResult solveOptimalAd(Game game, List<Ad> ads) {
        return adService.solveAd(game.getGameId(), getOptimalAd(ads).getAdId());
    }

    private Ad getOptimalAd(List<Ad> ads) {
        return ads.stream()
                .map(ad -> ad.getEncrypted() == 1 ? AdDecoder.decode(ad) : ad)
                .sorted(Comparator.comparingDouble(AdController::computeAdScore))
                .filter(ad -> !ad.getMessage().contains("Steal")) // Stealing is bad
                .collect(Collectors.toList())
                .get(0);
    }

    public List<Ad> retrieveAds(String gameId) {
        return adService.getAds(gameId);
    }
}
