package Players;

import Cards.Cards;

import java.util.ArrayList;

public class Bot {
    private ArrayList<Cards> cards;
    private boolean stayBlackjack;
    private boolean bustBlackjack;
    private boolean foldPoker;
    private boolean matchingHighestBetPoker;
    private int bet = 0;
    private int combinationValuePoker = 0;


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

    public boolean isMatchingHighestBetPoker() {
        return matchingHighestBetPoker;
    }

    public void setMatchingHighestBetPoker(boolean matchingHighestBetPoker) {
        this.matchingHighestBetPoker = matchingHighestBetPoker;
    }

    public boolean isFoldPoker() {
        return foldPoker;
    }

    public void setFoldPoker(boolean foldPoker) {
        this.foldPoker = foldPoker;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getCombinationValuePoker() {
        return combinationValuePoker;
    }

    public void setCombinationValuePoker(int combinationValuePoker) {
        this.combinationValuePoker = combinationValuePoker;
    }
}
