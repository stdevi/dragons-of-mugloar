package com.stdevi.dragonsofmugloar.model.message;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Ad {
    private String adId;
    private String message;
    private String reward;
    private int expiresIn;
}
