package Games.Poker;

import Cards.Cards;
import Players.Bot;
import Players.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Poker {

    private final Cards cards = new Cards();
    private final Random rand = new Random();
    private boolean endOfGame = false;
    private int choice = 0;
    private int luck = 0;
    private boolean correct = false;
    private ArrayList<Bot> bots = new ArrayList<>();
    private int throwawayInt = 0;
    private int throwaway2Int = 0;
    private Scanner sc = new Scanner(System.in);
    private int totalCall = 0;
    private int bet = 25;
    private int amountCall = 0;
    private int highestBet = 0;
    private PokerPlays pokerPlays = new PokerPlays();
    private boolean betSeetled = false;
    private ArrayList<Cards> pokerHand = new ArrayList<>();
    private PokerCombinations pokerCombinations = new PokerCombinations();
    private int userCombinationValuePoker = 0;
    private boolean userMatchingHighestBetPoker;



    public void pokerGame(User user, int numberOfBots){
        user.winnings(-bet);

        for (int i = 0; i < 2; i++) {
            userDrawCards(user);
        }

        for (int i = 0; i < numberOfBots; i++) {
            bots.add(new Bot());
            for (int j = 0; j < 2; j++) {
                bots.get(i).getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
            }
        }

        while (!endOfGame) {
            while (!betSeetled) {
                bettingRound(user);
            }

            if (checkIfEverybodyFolded(user)){// 3 cards
                for (int i = 0; i < 3; i++) {
                    pokerHand.add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
                }
                System.out.print("Poker hand: ");
                writeDeck(pokerHand);
                System.out.println(" ");

                while (!betSeetled) {
                    bettingRound(user);
                }
                if (checkIfEverybodyFolded(user)){ // 4 cards
                    pokerHand.add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
                    System.out.print("Poker hand: ");
                    writeDeck(pokerHand);
                    System.out.println(" ");

                    while (!betSeetled) {
                        bettingRound(user);
                    }
                    if (checkIfEverybodyFolded(user)){// 5 cards
                        pokerHand.add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
                        System.out.print("Poker hand: ");
                        writeDeck(pokerHand);
                        System.out.println(" ");

                        while (!betSeetled) {
                            bettingRound(user);
                        }

                        winOrLose(user);

                    }else {
                        winOrLose(user);
                    }
                }else {
                    winOrLose(user);
                }
            }else {
                winOrLose(user);
            }
        }
    }

    public void userDrawCards(User user){
        if (user.getLuck() == 0) {
            luck = rand.nextInt(100) + 1;
            if (luck <= user.getLuck()) {
                user.getCards().add(cards.getCards().get(rand.nextInt(20)));
            } else {
                user.getCards().add(cards.getCards().get(rand.nextInt(52)));
            }
        }
    }

    public void winOrLose(User user){
        userCombinationValuePoker = pokerCombinations.checkCombination(user.getCards());
        for (Bot bot : bots) {
            bot.setCombinationValuePoker(pokerCombinations.checkCombination(bot.getCards()));
        }

        throwawayInt = 0;
        int ID = 0;
        for (int i = 0; i < bots.size(); i++) {
            if (throwawayInt > bots.get(i).getCombinationValuePoker()){
                throwawayInt = bots.get(i).getCombinationValuePoker();
                ID = i;
            }
        }

        if (userCombinationValuePoker != 0 && throwawayInt != 0) {
            if (userCombinationValuePoker < throwawayInt) {
                System.out.println("Bot " + ID + " won!");
            }else {
                System.out.println("You " + ID + " won!");
            }
        }else {
            if (highestCardCombination(user) != 0){
                System.out.println("Bot " + ID + " won!");
            }else {
                System.out.println("You " + ID + " won!");
            }
        }
        endOfGame = true;
    }

    public int highestCardCombination(User user){
        throwawayInt = 0;
        throwaway2Int = 0;
        int ID = 0;
        for (int i = 0; i < bots.size(); i++) {
            if (pokerCombinations.checkCombination(bots.get(i).getCards()) < throwawayInt &&
                    pokerCombinations.checkCombination(bots.get(i).getCards()) != 1){
                throwawayInt = pokerCombinations.checkCombination(bots.get(i).getCards());
                ID = i;
            }

        }
        if (pokerCombinations.checkCombination(user.getCards()) != 1){
            throwaway2Int = pokerCombinations.checkCombination(user.getCards());

        }
        if (throwawayInt<throwaway2Int){
            return 0;
        }else {
            return ID;
        }

    }

    public void writeDeck(ArrayList<Cards> cards){
        for (Cards card : cards) {
            System.out.println("(" + card.getFace() + " "
                    + card.getSuit() + " "
                    + card.getValue_in_blackjack() + ") ");
        }
        System.out.println(" ");
    }

    public boolean checkIfIsTheHighestBet(User user){
        throwawayInt = 0;
        betSeetled = false;

        for (int i = 0; i < bots.size() + 1; i++) {
            if (throwawayInt > bots.get(i).getBet()) {
                bots.get(i).setMatchingHighestBetPoker(true);
                throwawayInt = bots.get(i).getBet();
                throwaway2Int++;
            }else {
                bots.get(i).setMatchingHighestBetPoker(false);
            }
            if (throwawayInt > bet){
                userMatchingHighestBetPoker = true;
                throwawayInt = bet;
                throwaway2Int++;
            }else {
                userMatchingHighestBetPoker = false;
            }
            if (throwaway2Int == bots.size()+1){
                betSeetled = true;
                return true;
            }
        }
        return false;

    }

    public void bettingRound(User user){
        while (checkIfIsTheHighestBet(user)) {
            System.out.println("Your cards: ");
            writeDeck(user.getCards());

            if (!user.isFoldPoker()) {
                System.out.println("""
                        Do you want to:
                        1) fold
                        2) call
                        3) bet
                        """);
                while (!correct) {
                    try {
                        choice = sc.nextInt();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    switch (choice) {
                        case 1: // FOLD
                            pokerPlays.userFold(user);
                            correct = true;
                        case 2: // CALL
                            pokerPlays.userCall(user, bet, userMatchingHighestBetPoker, highestBet);
                            correct = true;
                        case 3: // BET
                            pokerPlays.userBet(user, bet);
                            correct = true;

                        default:
                            System.out.println("Invalid choice");

                    }
                }
            }
            correct = false;

            throwawayInt = 0;
            for (Bot bot : bots) {
                throwawayInt = rand.nextInt(100);
                if (10 > throwawayInt) {
                    pokerPlays.botFold(bot);
                    bots.remove(bot);
                }
                if (10 < throwawayInt && throwawayInt > 90) {
                    pokerPlays.botCall(bot, bot.isMatchingHighestBetPoker(), bot.getBet());
                }
                if (throwawayInt < 90) {
                    pokerPlays.botBet(bot, bot.getBet());
                }
            }
        }
    }

    public boolean checkIfEverybodyFolded(User user){
        throwawayInt = 0;
        for (int i = 0; i < bots.size() + 1; i++) {
            if (bots.get(i).isFoldPoker()){
                throwawayInt++;
            }
            if (user.isFoldPoker()){
                throwawayInt++;
            }
        }
        return throwawayInt != bots.size() + 1;
    }
}
