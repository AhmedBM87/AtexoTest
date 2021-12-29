package com.atexo.card.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.atexo.card.model.CardPack;
import com.atexo.card.model.Hand;
import com.atexo.exception.CardNotFoundException;
import com.atexo.service.ICardPackService;
import com.atexo.service.IPlayerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/atexo/")
public class CardResources {
	
	@Autowired
	private ICardPackService cardPackService;

	@Autowired
	private IPlayerService playerService;

	private  Hand hand = null;
	private  CardPack cardPack = null;
	
	@Operation(tags = "Card", summary = "Action Card", description = "pull", responses = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "206", description = "Partial response"),
			@ApiResponse(responseCode = "404", description = "No card found"),
			@ApiResponse(responseCode = "500", description = "Server_error : something went wrong") })
	@GetMapping(value = "/card")
	public ResponseEntity<?> randomCard(            
			 @Schema(type = "String", allowableValues = {"initPack", "getCard", "sortCard"}) String action
) throws CardNotFoundException {

		
		switch(action) {
		case "initPack":{
			 cardPack = cardPackService.initialiserPack();
			return new ResponseEntity<CardPack>(cardPack, HttpStatus.OK);

		}
		case "getCard":{
			 hand = playerService.pullHand(cardPack);
			return new ResponseEntity<Hand>(hand, HttpStatus.OK);

		}
		case "sortCard":{
			 hand=playerService.sortCard(hand);
			return new ResponseEntity<Hand>(hand, HttpStatus.OK);

		}default: {
			return new ResponseEntity<Object>("Error", HttpStatus.INTERNAL_SERVER_ERROR);

		} 
		}

	}
	@GetMapping(value = "/hand-cards")
    public ModelAndView cardData() throws CardNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("card-data");

		CardPack pack = cardPackService.initialiserPack();
		modelAndView.addObject("cards", pack.getCards());

		Hand handCards = playerService.pullHand(pack);
		modelAndView.addObject("randomHandCards", handCards.getCards());

		Hand storedhandCards = playerService.sortCard(handCards);
		modelAndView.addObject("storedHandCards", storedhandCards.getCards());
		return modelAndView;
    }

}
