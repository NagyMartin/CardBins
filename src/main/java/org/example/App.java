package org.example;

import com.opencsv.exceptions.CsvValidationException;
import controller.CardController;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws CsvValidationException, IOException {

        System.out.println( "Hello World!" );

        CardController cardController = new CardController();
        cardController.cardListReader("C:\\Users\\Martin\\Desktop\\Projects\\CardBins\\src\\main\\cards.txt");

    }
}
