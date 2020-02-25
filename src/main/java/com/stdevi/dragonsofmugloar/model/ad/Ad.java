package com.stdevi.dragonsofmugloar.model.ad;

import lombok.Data;

@Data
public class Ad {
    private String adId;
    private String message;
    private String reward;
    private int expiresIn;
    private int encrypted;
    private String probability;

    public int getLevelOfDifficulty() {
        switch (probability) {
            case "Piece of cake":
            case "Walk in the park":
            case "Sure thing":
                return AdProbability.Level1.getLevel();
            case "Quite likely":
                return AdProbability.Level2.getLevel();
            case "Hmmm....":
                return AdProbability.Level3.getLevel();
            case "Gamble":
                return AdProbability.Level4.getLevel();
            case "Risky":
                return AdProbability.Level5.getLevel();
            case "Rather detrimental":
                return AdProbability.Level6.getLevel();
            case "Playing with fire":
                return AdProbability.Level7.getLevel();
            case "Suicide mission":
                return AdProbability.Level8.getLevel();
            case "Impossible":
            default:
                return AdProbability.Level9.getLevel();
        }
    }

    @Override
    public String toString() {
        return message +
                "reward:" + reward +
                "expiresIn:" + expiresIn +
                "probability:" + probability;
    }
}
