package controller;

import lombok.Getter;
import lombok.Setter;
import model.Card;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CardController {

    private List<BigDecimal> migratedList = new ArrayList<>();
    private List<BigDecimal> dropCardList = new ArrayList<>();


    public List<Card> cardListReader(String filePath) {
        List<Card> cardList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] nextLine;
            while ((line=br.readLine()) != null) {
                nextLine = line.split(",");
                    Card card = new Card();
                    card.setPaymentMethodID(BigDecimal.valueOf(Double.parseDouble(nextLine[0])));
                    card.setCardCountryCode(nextLine[1]);
                    card.setCardScheme(nextLine[2]);
                    card.setLast4Digits(nextLine[3]);
                    card.setBinRange(BigDecimal.valueOf(Double.parseDouble(nextLine[4])));
                    card.setCardExpiry(nextLine[5]);
                    cardList.add(card);
            }
            cardSchemeValidation(cardList);
            System.out.println(cardList);
        } catch (IOException e) {
            System.out.println("There was an issue with reading the cards list: " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return cardList;
    }

    public void cardSchemeValidation(List<Card> cardList){
        try{
            for(Card card : cardList){
                if (card.getCardScheme()== null || card.getCardScheme().isEmpty()) {
                    cardSorter(card.getBinRange(), card, cardList);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cardSorter(BigDecimal binNumber, Card card, List<Card> sortedCardList){
        try{
          if(migratedList.contains(binNumber))  {
              card.setCardScheme("DEFAULT");
          }
          if(dropCardList.contains(binNumber)){
              sortedCardList.remove(card);
          }
          else {
              sortedCardList.add(card);
          }
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Could not sort the card with binNumber" + binNumber + e.getLocalizedMessage());
        }
    }

}

