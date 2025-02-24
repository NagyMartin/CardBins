package service;

import lombok.Getter;
import lombok.Setter;
import model.Card;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CardService {

    private List<Card> cardList = new ArrayList<>();

    public List<Card> cardFileToList(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines()
                    .map(line -> {
                        String[] nextLine = line.split(",");
                        Card card = new Card();
                        card.setPaymentMethodID(BigDecimal.valueOf(Double.parseDouble(nextLine[0])));
                        card.setCardCountryCode(nextLine[1]);
                        card.setCardScheme(nextLine[2].isEmpty() ? null : nextLine[2]);
                        card.setLast4Digits(nextLine[3]);
                        card.setBinRange(BigDecimal.valueOf(Double.parseDouble(nextLine[4])));
                        card.setCardExpiry(nextLine[5]);
                        return card;
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("There was an issue with reading the cards list: " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public void cardChecker(List<BigDecimal> migratedCard, List<BigDecimal> droppedCard) {
        List<Card> cardsToRemove = new ArrayList<>();
        try {
            for (Card c : cardList) {
                if (c.getCardScheme() == null) {
                    if (migratedCard.contains(c.getBinRange())) {
                        c.setCardScheme("DEFAULT");
                    } else if (droppedCard.contains(c.getBinRange())) {
                        cardsToRemove.add(c);
                    }
                }
            }
            cardList.removeAll(cardsToRemove);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
