package com.stdevi.dragonsofmugloar.model.ad;

public enum AdProbability {
    Level1(1),
    Level2(2),
    Level3(3),
    Level4(4),
    Level5(5),
    Level6(6),
    Level7(7),
    Level8(8),
    Level9(9);

    private int level;

    AdProbability(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
