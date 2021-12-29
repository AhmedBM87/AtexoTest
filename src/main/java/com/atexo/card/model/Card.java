package com.atexo.card.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {

    private CardValues value;

    private CardColor color;


    @Override
    public String toString()
    {
        return this.value.getValue() + " de " + this.color.getValue();
    }
}
