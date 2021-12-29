package com.atexo.card.model;



import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardColor {
    CARREAU("carreau"),
    COEUR("coeur"),
    PIQUE("pique"),
    TREFLE("trefle");

    private String value;
}
