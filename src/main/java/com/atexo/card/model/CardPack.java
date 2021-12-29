package com.atexo.card.model;


import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CardPack {

	@Builder.Default
	private List<Card> cards = new ArrayList<Card>(52);

	

	
}
