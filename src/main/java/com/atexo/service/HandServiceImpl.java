package com.atexo.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.atexo.card.model.Card;
import com.atexo.card.model.CardPack;
import com.atexo.card.model.Hand;
import com.atexo.exception.CardNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HandServiceImpl implements IHandService {
	 

	@Override
	public Hand  initialiserHand(CardPack pack) throws CardNotFoundException {
		log.info("Start initialiserHand");
		
		if(pack==null || pack.getCards().isEmpty())
		{
			throw new CardNotFoundException("No Card was found");
		}
		Hand hand = Hand.builder().build();
		Random rand = new Random();
		while (hand.getCards().size() < 10) {
			Card carte = pack.getCards().get(rand.nextInt(pack.getCards().size()));
			if (!hand.getCards().contains(carte)) {
				hand.getCards().add(carte);
			}
		}
		log.info("rondom card {} " , hand.getCards());
		return hand;
	}
}
