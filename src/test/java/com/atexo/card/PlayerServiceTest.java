package com.atexo.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atexo.card.model.Card;
import com.atexo.card.model.CardColor;
import com.atexo.card.model.CardPack;
import com.atexo.card.model.CardValues;
import com.atexo.card.model.Hand;
import com.atexo.exception.CardNotFoundException;
import com.atexo.service.ICardPackService;
import com.atexo.service.IHandService;
import com.atexo.service.IPlayerService;

@SpringBootTest(classes = CardApplication.class)
public class PlayerServiceTest {
	@Autowired
	private ICardPackService cardPackService;
	@Autowired
	private IPlayerService playerService;
	@Autowired
	private IHandService handService;
	private CardPack cardPack;

	@BeforeEach
	void setUp() {
		cardPack = cardPackService.initialiserPack();
	}

	@Test
	void PullHantTest() throws CardNotFoundException {
		Hand handCard = handService.initialiserHand(cardPack);
		assertEquals(handCard.getCards().size(), 10);
		assertEquals(cardPack.getCards().size(), 52);

	}

	@Test
	void sortCardTest() throws CardNotFoundException {
		List<Card> handCards = Arrays.asList(Card.builder().value(CardValues.ROI).color(CardColor.TREFLE).build(),
				Card.builder().value(CardValues.VALET).color(CardColor.COEUR).build(),
				Card.builder().value(CardValues.AS).color(CardColor.PIQUE).build(),
				Card.builder().value(CardValues.CINQ).color(CardColor.TREFLE).build(),
				Card.builder().value(CardValues.DAME).color(CardColor.COEUR).build(),
				Card.builder().value(CardValues.SEPT).color(CardColor.TREFLE).build(),
				Card.builder().value(CardValues.AS).color(CardColor.CARREAU).build(),
				Card.builder().value(CardValues.NEUF).color(CardColor.COEUR).build(),
				Card.builder().value(CardValues.CINQ).color(CardColor.CARREAU).build(),
				Card.builder().value(CardValues.TROIS).color(CardColor.TREFLE).build());

		Hand handCard = Hand.builder().cards(handCards).build();
		handCard = playerService.sortCard(handCard);
		assertEquals(CardValues.AS.getValue(), handCard.getCards().get(0).getValue().getValue());
		assertEquals(CardColor.CARREAU.getValue(), handCard.getCards().get(0).getColor().getValue());
		assertEquals(CardValues.CINQ.getValue(), handCard.getCards().get(1).getValue().getValue());
		assertEquals(CardColor.CARREAU.getValue(), handCard.getCards().get(1).getColor().getValue());
	}

	@Test
	void sortCartTest_throwCardNotFoundException_WhenNoCardInHand() throws CardNotFoundException {
		Hand handCardEmpty = Hand.builder().build();
		assertThrows(CardNotFoundException.class, () -> {
			playerService.sortCard(handCardEmpty);
		});

	}

	@Test
	void sortCartTest_throwCardNotFoundException_WhenHandIsNull() throws CardNotFoundException {
		Hand handCardNull = null;
		assertThrows(CardNotFoundException.class, () -> {
			playerService.sortCard(handCardNull);
		});

	}

	@Test
	void pullCartTest_throwCardNotFoundException_WhenNoCardInPack() throws CardNotFoundException {
		CardPack pack = CardPack.builder().build();
		assertThrows(CardNotFoundException.class, () -> {
			handService.initialiserHand(pack);
		});

	}

	@Test
	void pullCartTest_throwCardNotFoundException_WhenPackIsNull() throws CardNotFoundException {
		CardPack pack = null;
		assertThrows(CardNotFoundException.class, () -> {
			handService.initialiserHand(pack);
		});

	}

}
