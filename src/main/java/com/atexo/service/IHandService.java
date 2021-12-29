package com.atexo.service;

import com.atexo.card.model.CardPack;
import com.atexo.card.model.Hand;
import com.atexo.exception.CardNotFoundException;

public interface IHandService {

	Hand initialiserHand(CardPack cardPack) throws CardNotFoundException;

}
