package com.atexo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.atexo.card.model.Card;
import com.atexo.card.model.CardPack;
import com.atexo.card.model.CardColor;
import com.atexo.card.model.CardValues;

@Service
public class CardPackServiceImpl implements ICardPackService {

	@Override
	public CardPack initialiserPack() {
		List<Card> cards = new ArrayList<>();
		Stream.of(CardColor.values()).forEachOrdered(color -> Stream.of(CardValues.values())
				.forEachOrdered(value -> cards.add(Card.builder().color(color).value(value).build())));
		Collections.shuffle(cards);
		CardPack cardPack = CardPack.builder().cards(cards).build();

		return cardPack;
	}
}
