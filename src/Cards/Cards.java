package Cards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for the cards.
 */
public class Cards {
    /**
     * Variables for the class.
     */
    private String face;
    private String suit;
    private String color;
    private int value_in_poker;
    private int value_in_blackjack;
    private ArrayList<Cards> cards = new ArrayList<>();


    /**
     * Constructors for the class.
     */
    public Cards(String face, String suit, String color, int value_in_poker, int value_in_blackjack) {
        this.face = face;
        this.suit = suit;
        this.color = color;
        this.value_in_poker = value_in_poker;
        this.value_in_blackjack = value_in_blackjack;
    }

    public Cards() {
    }


    /**
     * Method to load cards from the Cards.csv file into an ArrayList.
     */
    public void loadCards() {
        try (BufferedReader br = new BufferedReader(new FileReader("Cards.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                Cards cards1 = new Cards(
                        split[0],
                        split[1],
                        split[2],
                        Integer.parseInt(split[3]),
                        Integer.parseInt(split[4])
                );
                cards.add(cards1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Setters and getters.
     */
    public String getFace() {
        return face;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue_in_poker() {
        return value_in_poker;
    }

    public int getValue_in_blackjack() {
        return value_in_blackjack;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

}
