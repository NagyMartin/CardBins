package org.example;

import com.opencsv.exceptions.CsvValidationException;
import controller.CardController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws CsvValidationException, IOException {

        System.out.println( "Hello World!" );

        CardController cardController = new CardController();
        List<BigDecimal> droppedCard = new ArrayList<>();
        droppedCard.add(BigDecimal.valueOf(6655));
        cardController.setDropCardList(droppedCard);
        System.out.println(droppedCard);
        cardController.cardListReader("C:\\Users\\Martin\\Desktop\\Projects\\CardBins\\src\\main\\cards.txt");

    }
}
