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

        String filepath = "C:\\Users\\Martin\\Desktop\\Projects\\CardBins\\src\\main\\cards.txt";

        CardController cardController = new CardController();
        List<BigDecimal> migratedCard = new ArrayList<>();
        List<BigDecimal> droppedCard = new ArrayList<>();

        migratedCard.add(BigDecimal.valueOf(63));
        droppedCard.add(BigDecimal.valueOf(Double.parseDouble("6655")));

        cardController.setMigratedList(migratedCard);
        cardController.setDropCardList(droppedCard);

        System.out.println(droppedCard);
        cardController.setFilepath(filepath);
        System.out.println(cardController.startCheck());

    }
}
