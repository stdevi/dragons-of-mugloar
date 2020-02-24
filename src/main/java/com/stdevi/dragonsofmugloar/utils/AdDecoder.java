package com.stdevi.dragonsofmugloar.utils;

import com.stdevi.dragonsofmugloar.model.ad.Ad;

import java.util.Base64;

public class AdDecoder {

    public static Ad decode(Ad ad) {
        Base64.Decoder decoder = Base64.getDecoder();
        ad.setAdId(new String(decoder.decode(ad.getAdId())));
        ad.setMessage(new String(decoder.decode(ad.getMessage())));
        ad.setProbability(new String(decoder.decode(ad.getProbability())));

        return ad;
    }
}
