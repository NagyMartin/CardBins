package controller;

import lombok.Getter;
import lombok.Setter;
import model.Card;
import service.CardService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CardController {

    private List<BigDecimal> migratedList = new ArrayList<>();
    private List<BigDecimal> dropCardList = new ArrayList<>();
    private CardService cardService = new CardService();
    private String filepath;

    public List<Card> startCheck(){
        cardService.setCardList(cardService.cardFileToList(filepath));
        cardService.cardChecker(migratedList, dropCardList);
        return cardService.getCardList();
    }

}

