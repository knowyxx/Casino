package Players;

import Cards.Cards;

import java.util.ArrayList;

/**
 * Class for bots.
 */
public class Bot {
    private ArrayList<Cards> cards;
    private boolean stayBlackjack;
    private boolean bustBlackjack;
    private boolean isCounted;
    private boolean foldPoker;
    private boolean matchingHighestBetPoker;
    private int bet = 0;
    private int combinationValuePoker = 0;


    /**
     * Constructor for class.
     */
    public Bot() {
        cards = new ArrayList<Cards>();
    }

    /**
     * Setters and getters.
     */
    public ArrayList<Cards> getCards() {
        return cards;
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

    public boolean isCounted() {
        return isCounted;
    }

    public void setCounted(boolean counted) {
        isCounted = counted;
    }
}
