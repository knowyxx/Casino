package Cards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cards {
    private String face;
    private String suit;
    private String color;
    private int value_in_poker;
    private int value_in_blackjack;
    private ArrayList<Cards> cards = new ArrayList<>();

    public Cards(String face, String suit, String color, int value_in_poker, int value_in_blackjack) {
        this.face = face;
        this.suit = suit;
        this.color = color;
        this.value_in_poker = value_in_poker;
        this.value_in_blackjack = value_in_blackjack;
    }

    public Cards(String face, String suit, String color, int value_in_poker, int value_in_blackjack, ArrayList<Cards> cards) {
        this.face = face;
        this.suit = suit;
        this.color = color;
        this.value_in_poker = value_in_poker;
        this.value_in_blackjack = value_in_blackjack;
        this.cards = cards;
    }

    public Cards() {
    }

    public void loadCards() throws FileNotFoundException {
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

    public void shuffleCards(){

    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getValue_in_poker() {
        return value_in_poker;
    }

    public void setValue_in_poker(int value_in_poker) {
        this.value_in_poker = value_in_poker;
    }

    public int getValue_in_blackjack() {
        return value_in_blackjack;
    }

    public void setValue_in_blackjack(int value_in_blackjack) {
        this.value_in_blackjack = value_in_blackjack;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Cards> cards) {
        this.cards = cards;
    }
}
