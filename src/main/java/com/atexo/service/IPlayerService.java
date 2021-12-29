package com.atexo.service;

import com.atexo.card.model.CardPack;
import com.atexo.card.model.Hand;
import com.atexo.exception.CardNotFoundException;

public interface IPlayerService {

	Hand pullHand(CardPack cardPack) throws CardNotFoundException;

	Hand sortCard(Hand hand) throws CardNotFoundException;

}
