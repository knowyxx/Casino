package Games.Blackjack;

import Cards.Cards;
import Players.User;
import Players.Bot;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class for the game blackjack.
 */
public class Blackjack {

    private final Cards cards = new Cards();
    private final Random rand = new Random();
    private final Bot dealer = new Bot();
    private final ArrayList<Bot> bots = new ArrayList<Bot>();
    private boolean correct = false;
    private final Scanner sc = new Scanner(System.in);


    /**
     * Main method for starting the game.
     */
    public void blackjackGame(User user, int bet, int numberOfBots) {
        cards.getCards().clear();
        bots.clear();
        dealer.getCards().clear();
        boolean endOfGame = false;
        boolean userBustBlackjack = false;
        boolean userStay = false;
        System.out.println("""
                Welcome to blackjack!
                Blackjack pays 3 to 2
                Dealer must draw to 16 and stand on 17
                """);
        cards.loadCards();
        /*
            Loading cards into the deck of the user.
         */
        for (int i = 0; i < 2; i++) {
            user.getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
        }

        /*
            Loading the cards into the deck of the dealer.
         */
        for (int i = 0; i < 2; i++) {
            dealer.getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
        }

        /*
            Loading cards into the deck of chosen number of bots.
         */
        for (int i = 0; i < numberOfBots; i++) {
            bots.add(new Bot());
            for (int j = 0; j < 2; j++) {
                bots.get(i).getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
            }
        }
        /*
            Showing the dealers' deck except the second one, which is hidden.
         */
        System.out.println("Dealers cards: ");
        System.out.println("( " + dealer.getCards().get(0).getFace() + " "
                + dealer.getCards().get(0).getSuit() + " "
                + dealer.getCards().get(0).getValue_in_blackjack() + " )");
        System.out.println("( # # # )");
        System.out.println(" ");

        int totalStay = 0;
        int totalBust = 0;

        /*
            Starting the game.
         */
        while (!endOfGame) {
            /*
                Integer that increases in size when a player or bot stays or busts.
             */




            /*
                Write out users' deck.
             */
            System.out.println("Your cards: ");
            writeDeck(user.getCards());
            System.out.println(" ");

            if (checkIfUserBusted(user) && !userBustBlackjack) {
                userBustBlackjack = true;
                totalBust++;
                user.winnings(-bet);
                System.out.println("You busted!");
            }


            /*
                First the bots either hit or stay and then write out the deck of the bots and check if they have busted.
             */
            for (int i = 0; i < bots.size(); i++) {
                botBlackjackGame(bots.get(i));
                System.out.println("Bot" + i+1 + " cards: ");
                writeDeck(bots.get(i).getCards());

                if (checkIfBotBusted(bots.get(i))){
                    System.out.println("Bot" + i+1 + " busted!");
                    totalBust++;
                }
                if (bots.get(i).isStayBlackjack() && !bots.get(i).isCounted()){
                    bots.get(i).setCounted(true);
                    totalStay++;
                }
            }

            /*
                User can either hit or stay.
             */
            if (!userStay && !userBustBlackjack) {
                System.out.println("""
                    Do you want to:
                    1) hit
                    2) stay""");
                while (!correct) {
                    try {
                        int choice = Integer.parseInt(sc.nextLine());
                        switch (choice) {
                            case 1: // HIT
                                int luck = rand.nextInt(100) + 1;
                                if (luck <= user.getLuck()) {
                                    user.getCards().add(cards.getCards().get(rand.nextInt(20)));
                                } else {
                                    user.getCards().add(cards.getCards().get(rand.nextInt(52)));
                                }

                                correct = true;
                                break;
                            case 2: // STAY
                                totalStay++;
                                userStay = true;
                                correct = true;
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println(e.getMessage());
                    }
                }
            }

            int playersOut = 0;
            if (userBustBlackjack || userStay) {
                playersOut++;
            }
            for (Bot bot : bots) {
                if (bot.isBustBlackjack() || bot.isStayBlackjack()) {
                    playersOut++;
                }
            }
            if (playersOut == numberOfBots + 1) {
                endOfGame = true;
            }
            correct = false;
            /*
                If the user and every bot stays and or busts, then the dealer starts drawing cards.
             */
            if (totalStay + totalBust == numberOfBots + 1) {
                endOfGame = true;
            }
        }



        int totalValueDealer = 0;
        int totalValueUser = 0;

        /*
            To check if the dealers first 2 cards value is already above 17.
         */
        for (int i = 0; i < dealer.getCards().size(); i++) {
            totalValueDealer = totalValueDealer + dealer.getCards().get(i).getValue_in_blackjack();
        }

        for (int i = 0; i < user.getCards().size(); i++) {
            totalValueUser = totalValueUser + user.getCards().get(i).getValue_in_blackjack();
        }

        /*
            Dealer must draw cards until the value of his deck is greater or the same as 17 and write out his deck.
         */
        System.out.println("Dealers cards: ");
        if (totalValueDealer<17) {
            while (totalValueDealer < 17) {
                dealer.getCards().add(cards.getCards().get(rand.nextInt(52)));
                writeDeck(dealer.getCards());
                System.out.println(" ");
                for (int i = 0; i < dealer.getCards().size(); i++) {
                    totalValueDealer = totalValueDealer + dealer.getCards().get(i).getValue_in_blackjack();
                }
            }
        }else {
            writeDeck(dealer.getCards());
        }
        System.out.println(" ");

        /*
            Possibilities of the game ending.
         */
        if (totalValueDealer>21){
            System.out.println("Dealer busted!");
            if (!checkIfUserBusted(user)){
                user.winnings((double) (bet * 3) /2);
            }
        } else if (totalValueDealer<totalValueUser&&!checkIfUserBusted(user)) {
            System.out.println("You beat the dealer!");
            user.winnings((double) (bet * 3) /2);
        } else if (totalValueDealer>totalValueUser&&!checkIfUserBusted(user)) {
            user.winnings(-bet);
            System.out.println("You lost to the dealer!");
        } else if (totalValueDealer==totalValueUser&&!checkIfUserBusted(user)) {
            System.out.println("Have the same cards value as the dealer, what a coincidence!");
        }
    }

    /**
     * Bots either draw a card or stay depending on their deck value.
     */
    private void botBlackjackGame(Bot bot) {
        int totalValue = 0;
        if (!bot.isStayBlackjack()&&!bot.isBustBlackjack()) {
            for (int i = 0; i < bot.getCards().size(); i++) {
                totalValue = totalValue + bot.getCards().get(i).getValue_in_blackjack();
            }
            if (totalValue <= 14) {
                bot.getCards().add(cards.getCards().get(rand.nextInt(52)));
            } else {
                bot.setStayBlackjack(true);
            }
        }
    }

    /**
     * Check if users deck value is over 21.
     */
    private boolean checkIfUserBusted(User user){
        int totalValue = 0;
        for (int i = 0; i < user.getCards().size(); i++) {
            totalValue = totalValue + user.getCards().get(i).getValue_in_blackjack();
        }
        return totalValue > 21;
    }

    /**
     * Check if bots' deck value is over 21.
     */
    private boolean checkIfBotBusted(Bot bot){
        int totalValue = 0;
        for (int i = 0; i < bot.getCards().size(); i++) {
            totalValue = totalValue + bot.getCards().get(i).getValue_in_blackjack();
        }
        return totalValue > 21;
    }

    /**
     * Write out the cards in the deck.
     */
    private void writeDeck(ArrayList<Cards> cards){
        for (Cards card : cards) {
            System.out.println("(" + card.getFace() + " "
                    + card.getSuit() + " "
                    + card.getValue_in_blackjack() + ") ");
        }
        System.out.println(" ");
    }
}
