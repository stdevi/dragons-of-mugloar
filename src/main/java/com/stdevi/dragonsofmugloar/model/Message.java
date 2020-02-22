package com.stdevi.dragonsofmugloar.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {
    private String adId;
    private String message;
    private String reward;
    private int expiresIn;
}