package Players;

import Cards.Cards;

import java.util.ArrayList;

public class Bot {
    private ArrayList<Cards> cards;
    private boolean stayBlackjack = false;
    private boolean bustBlackjack = false;


    public Bot() {
    }

    public Bot(ArrayList<Cards> cards, boolean stayBlackjack) {
        this.cards = cards;
        this.stayBlackjack = stayBlackjack;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Cards> cards) {
        this.cards = cards;
    }

    public boolean isStayBlackjack() {
        return stayBlackjack;
    }

    public void setStayBlackjack(boolean stayBlackjack) {
        this.stayBlackjack = stayBlackjack;
    }

    public boolean isBustBlackjack() {
        return bustBlackjack;
    }

    public void setBustBlackjack(boolean bustBlackjack) {
        this.bustBlackjack = bustBlackjack;
    }
}
