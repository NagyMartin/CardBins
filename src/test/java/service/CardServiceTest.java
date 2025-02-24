package service;

import model.Card;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardServiceTest {

    String filepath = "C:\\Users\\Martin\\Desktop\\Projects\\CardBins\\src\\main\\cards.txt";
    CardService cardService1 = new CardService();
    List<Card> cardList = new ArrayList<>();
    Card card1 = new Card(BigDecimal.valueOf(Double.parseDouble("3456")),"RO","WesternUnion",
            "1234",BigDecimal.valueOf(Double.parseDouble("8899")),"10/26");
    Card card2 = new Card(BigDecimal.valueOf(Double.parseDouble("1313")),"RO",null,
            "3345",BigDecimal.valueOf(Double.parseDouble("6655")),"09/28");


    //test that returns a list of cards from a txt file
    @Test
    void cardFileToList() {
        cardList.add(card1);
        cardList.add(card2);
        assertEquals(cardList, cardService1.cardFileToList(filepath));

    }
    //test that removes a null card scheme card from the list
    @Test
    void cardChecker() {
        List<BigDecimal> migratedCard = new ArrayList<>();
        List<BigDecimal> droppedCard = new ArrayList<>();
        droppedCard.add(BigDecimal.valueOf(Double.parseDouble("6655")));
        migratedCard.add(BigDecimal.valueOf(Double.parseDouble("7070")));
        List<Card> removedCardList = new ArrayList<>();
        Card card = new Card(BigDecimal.valueOf(Double.parseDouble("3456")),"RO","WesternUnion",
                "1234",BigDecimal.valueOf(Double.parseDouble("8899")),"10/26");
        removedCardList.add(card);
        cardList.add(card1);
        cardList.add(card2);
        cardService1.cardChecker(migratedCard, droppedCard);
        assertEquals(removedCardList, cardList);
    }
}