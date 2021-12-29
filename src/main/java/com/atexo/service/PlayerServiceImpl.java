package com.atexo.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atexo.card.model.Card;
import com.atexo.card.model.CardPack;
import com.atexo.card.model.Hand;
import com.atexo.exception.CardNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlayerServiceImpl implements IPlayerService{
	@Autowired
	private IHandService handService;

	@Override
	public Hand pullHand(CardPack cardPack) throws CardNotFoundException {
		Hand handCard = handService.initialiserHand(cardPack);
		return handCard;

	}

	@Override
	public Hand  sortCard(Hand hand) throws CardNotFoundException {
		log.info("Start sortHand");
		if(hand==null || hand.getCards().isEmpty()) {
			throw new CardNotFoundException("No Card was found");
		}
		List<Card> sortedList = hand.getCards().stream()
				.sorted(Comparator.comparing(Card::getColor).thenComparing(Comparator.comparing(Card::getValue)))
				.collect(Collectors.toList());
		hand.setCards(sortedList);
		log.info("sorted card: {}", sortedList);
		return hand;
	}
}
