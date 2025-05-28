package Games.Poker;

import Cards.Cards;
import Players.Bot;
import Players.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class for the poker game.
 */
public class Poker {

    private final Cards cards = new Cards();
    private final Random rand = new Random();
    private boolean endOfGame = false;
    private final ArrayList<Bot> bots = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final PokerPlays pokerPlays = new PokerPlays();
    private final ArrayList<Cards> pokerHand = new ArrayList<>();
    private final PokerCombinations pokerCombinations = new PokerCombinations();
    private int bet = 25;
    private int userBet = 0;
    private boolean userMatchingHighestBetPoker;
    private boolean userFoldPoker;
    private boolean betSettled = false;

    /**
     * Main method for starting poker.
     */
    public void pokerGame(User user, int numberOfBots) throws FileNotFoundException {
        /*
            Resetting some variables.
         */
        System.out.println("Welcome to poker!");
        cards.loadCards();
        user.winnings(-bet);
        userFoldPoker = false;
        userBet = bet;
        endOfGame = false;

        /*
            Giving the user and bots cards into their deck.
         */
        for (int i = 0; i < 2; i++) {
            userDrawCards(user);
        }

        for (int i = 0; i < numberOfBots; i++) {
            Bot bot = new Bot();
            for (int j = 0; j < 2; j++) {
                bot.getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
            }
            bots.add(bot);
        }

        /*
            Repeating functions in a loop that ends if everybody folded or the game ended.
         */
        while (!endOfGame) {
            betSettled = false;
            while (!betSettled) {
                bettingRound(user);
            }

            if (checkIfEverybodyFolded()) {
                for (int i = 0; i < 3; i++) {
                    pokerHand.add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
                }
                System.out.print("Community cards: ");
                writeDeck(pokerHand);

                betSettled = false;
                while (!betSettled) {
                    bettingRound(user);
                }

                if (checkIfEverybodyFolded()) {
                    pokerHand.add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
                    System.out.print("Community cards: ");
                    writeDeck(pokerHand);

                    betSettled = false;
                    while (!betSettled) {
                        bettingRound(user);
                    }

                    if (checkIfEverybodyFolded()) {
                        pokerHand.add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
                        System.out.print("Community cards: ");
                        writeDeck(pokerHand);

                        betSettled = false;
                        while (!betSettled) {
                            bettingRound(user);
                        }

                        winOrLose(user);
                    } else {
                        winOrLose(user);
                    }
                } else {
                    winOrLose(user);
                }
            } else {
                winOrLose(user);
            }
        }
    }

    /**
     * Give the user a card based on luck.
     */
    public void userDrawCards(User user) {
        int luck = rand.nextInt(100) + 1;
        if (luck <= user.getLuck()) {
            user.getCards().add(cards.getCards().get(rand.nextInt(20)));
        } else {
            user.getCards().add(cards.getCards().get(rand.nextInt(52)));
        }
    }

    /**
     * To check who won.
     */
    public void winOrLose(User user) {
        int userCombinationValuePoker = pokerCombinations.checkCombination(user.getCards());
        for (Bot bot : bots) {
            bot.setCombinationValuePoker(pokerCombinations.checkCombination(bot.getCards()));
        }

        int lowestBotCombo = 10000000;
        int winningBotId = -1;
        for (int i = 0; i < bots.size(); i++) {
            int combo = bots.get(i).getCombinationValuePoker();
            if (combo < lowestBotCombo) {
                lowestBotCombo = combo;
                winningBotId = i;
            }
        }

        if (userCombinationValuePoker != 0 && lowestBotCombo != 0) {
            if (userCombinationValuePoker < lowestBotCombo) {
                System.out.println("You won!");
            } else {
                System.out.println("Bot " + winningBotId + " won!");
            }
        } else {
            if (highestCardCombination(user) == 0) {
                System.out.println("You won!");
            } else {
                System.out.println("Bot " + winningBotId + " won!");
            }
        }

        /*
            Show all final hands
         */
        System.out.println("\n--- Final Cards ---");
        System.out.println("Your cards:");
        writeDeck(user.getCards());

        System.out.println("\nCommunity cards:");
        writeDeck(pokerHand);

        for (int i = 0; i < bots.size(); i++) {
            System.out.println("\nBot " + i + " cards:");
            writeDeck(bots.get(i).getCards());
        }

        endOfGame = true;
    }

    /**
     * To check, who has the highest card if no one has a combination.
     */
    public int highestCardCombination(User user) {
        int highestBot = 0;
        int ID = -1;
        for (int i = 0; i < bots.size(); i++) {
            int combo = pokerCombinations.checkCombination(bots.get(i).getCards());
            if (combo > highestBot) {
                highestBot = combo;
                ID = i;
            }
        }

        int userCombo = pokerCombinations.checkCombination(user.getCards());
        return (userCombo > highestBot) ? ID : 0;
    }

    /*
        Write deck.
     */
    public void writeDeck(ArrayList<Cards> cards) {
        for (Cards card : cards) {
            System.out.print("(" + card.getFace() + " " + card.getSuit() + ") ");
        }
        System.out.println();
    }

    /*
        Checks if the user or bot have the highest bet.
     */
    public boolean checkIfIsTheHighestBet() {
        int highest = bet;
        for (Bot bot : bots) {
            if (!bot.isFoldPoker()) {
                highest = Math.max(highest, bot.getBet());
            }
        }

        if (!userFoldPoker && userBet < highest) {
            userMatchingHighestBetPoker = false;
            return false;
        }

        for (Bot bot : bots) {
            if (!bot.isFoldPoker() && bot.getBet() < highest) {
                bot.setMatchingHighestBetPoker(false);
                return false;
            }
        }

        return true;
    }

    /*
        The betting round of poker for the user and bots.
     */
    public void bettingRound(User user) {
        while (!checkIfIsTheHighestBet()) {
            System.out.println("Your cards:");
            writeDeck(user.getCards());

            System.out.println("Your current bet: " + userBet);
            System.out.println("Total pot (approximate): " + calculateTotalPot());

            if (!userFoldPoker) {
                System.out.println("""
                        Do you want to:
                        1) fold
                        2) call
                        3) bet
                        """);

                boolean correct = false;
                while (!correct) {
                    int choice = sc.nextInt();
                    int highestBet = bet;

                    switch (choice) {
                        case 1:
                            pokerPlays.userFold();
                            userFoldPoker = true;
                            correct = true;
                            break;
                        case 2:
                            pokerPlays.userCall(user, bet, userMatchingHighestBetPoker, highestBet);
                            userBet = highestBet;
                            correct = true;
                            break;
                        case 3:
                            bet += 25;
                            userBet = bet;
                            pokerPlays.userBet(user, bet);
                            correct = true;
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                }
            }


            for (Bot bot : bots) {
                if (bot.isFoldPoker()) {
                    int roll = rand.nextInt(100);
                    if (roll < 10) {
                        pokerPlays.botFold(bot);
                        bots.remove(bot);
                    } else if (roll > 90) {
                        pokerPlays.botCall(bot, bot.isMatchingHighestBetPoker(), bot.getBet());
                    } else {
                        bet += 25;
                        pokerPlays.botBet(bot, bet);
                    }
                }
            }
        }

        betSettled = true;
    }

    /**
     * Checks if everybody has folded.
     */
    public boolean checkIfEverybodyFolded() {
        int foldCount = userFoldPoker ? 1 : 0;
        for (Bot bot : bots) {
            if (bot.isFoldPoker()) foldCount++;
        }
        return foldCount < bots.size() + 1;
    }

    /**
     * Calculates the amount of money in the pot.
     */
    private int calculateTotalPot() {
        int total = userBet;
        for (Bot bot : bots) {
            if (!bot.isFoldPoker()) total += bot.getBet();
        }
        return total;
    }
}
