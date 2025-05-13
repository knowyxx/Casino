package Games.Blackjack;

import Cards.Cards;
import Games.Players;
import Players.User;
import Players.Bot;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Blackjack implements Players {

    private final Cards cards = new Cards();
    private final Random rand = new Random();
    private Bot dealer = new Bot();
    private Bot bot1 = new Bot();
    private Bot bot2 = new Bot();
    private Bot bot3 = new Bot();
    private Bot bot4 = new Bot();
    private boolean endOfGame = false;
    private int choice = 0;
    private int luck;
    private boolean stay = false;
    private boolean correct = false;
    private Scanner sc = new Scanner(System.in);

    public void blackjackGame(User user, int bet, int numberOfBots) {
        //Loading cards into the deck of the user.
        for (int i = 0; i < 2; i++) {
            user.getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
        }

        //Loading the cards into the deck of the dealer.
        for (int i = 0; i < 2; i++) {
            dealer.getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
        }

        //Loading cards into the deck of chosen amount of bots.
        if (numberOfBots >= 1) {
            bot1.getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
        }

        if (numberOfBots >= 2) {
            bot2.getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
        }

        if (numberOfBots >= 3) {
            bot3.getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
        }

        if (numberOfBots == 4) {
            bot4.getCards().add(cards.getCards().get(rand.nextInt(cards.getCards().size())));
        }

        //Showing the dealers deck except the second one which is hidden.
        System.out.println("Dealers cards: " + dealer.getCards().get(0).getFace() + " "
                + dealer.getCards().get(0).getSuit() + " "
                + dealer.getCards().get(0).getValue_in_blackjack()
                + "# # #");


        //Starting the game.
        while (!endOfGame) {
            //Integer that increases in size when a player or bot stays or busts.
            int totalStay = 0;

            //User has the option to either hit or stay.
            if (!stay&&!checkIfUserBusted(user)) {
                System.out.println("""
                        Do you want to:
                        1) hit
                        2) stay""");
                while (!correct) {
                    try {
                        choice = sc.nextInt();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    switch (choice) {
                        case 1: // HIT
                            correct = true;
                            if (user.getLuck() == 0) {
                                luck = rand.nextInt(100) + 1;
                                if (luck <= user.getLuck()) {
                                    user.getCards().add(cards.getCards().get(rand.nextInt(20)));
                                } else {
                                    user.getCards().add(cards.getCards().get(rand.nextInt(52)));
                                }
                            }
                        case 2: // STAY
                            totalStay++;
                            stay = true;
                            correct = true;
                        default:
                            System.out.println("Invalid choice");
                    }
                }
            }

            //Write out users deck.
            System.out.println("Your cards: ");
            writeDeck(user.getCards());
            System.out.println(" ");

            if (checkIfUserBusted(user)) {
                totalStay++;
                System.out.println("Busted!");
            }


            //First the bots either hit or stay and then write out the deck of the bots and check if they have busted.
            if (numberOfBots >= 1) {
                botBlackjackGame(bot1);
                if (checkIfBotBusted(bot1)){
                    System.out.println("Bot1 busted");
                    totalStay++;
                }else {
                    System.out.println("Bot1 cards: ");
                    writeDeck(bot1.getCards());
                }
                if (bot1.isStayBlackjack()){
                    totalStay++;
                }
            }

            if (numberOfBots >= 2) {
                botBlackjackGame(bot2);
                if (checkIfBotBusted(bot2)){
                    System.out.println("Bot2 busted");
                    totalStay++;
                }else {
                    System.out.println("Bot2 cards: ");
                    writeDeck(bot2.getCards());
                }
                if (bot2.isStayBlackjack()){
                    totalStay++;
                }
            }

            if (numberOfBots >= 3) {
                botBlackjackGame(bot3);
                if (checkIfBotBusted(bot3)){
                    System.out.println("Bot3 busted");
                    totalStay++;
                }else {
                    System.out.println("Bot3 cards: ");
                    writeDeck(bot3.getCards());
                }
                if (bot3.isStayBlackjack()){
                    totalStay++;
                }
            }

            if (numberOfBots >= 4) {
                botBlackjackGame(bot4);
                if (checkIfBotBusted(bot4)){
                    System.out.println("Bot4 busted");
                    totalStay++;
                }else {
                    System.out.println("Bot4 cards: ");
                    writeDeck(bot4.getCards());
                }
                if (bot4.isStayBlackjack()){
                    totalStay++;
                }
            }


            //If the user and every bot stays and or busts, then the dealer starts drawing cards.
            if (totalStay == numberOfBots+1){
                int totalValueDealer = 0;
                int totalValueUser = 0;

                //To check if the dealers first 2 cards value is already above 17.
                for (int i = 0; i < dealer.getCards().size(); i++) {
                    totalValueDealer = totalValueDealer + dealer.getCards().get(i).getValue_in_blackjack();
                }

                //Dealer must draw cards until the value of his deck is greater or the same as 17 and write out his deck.
                if (totalValueDealer<17) {
                    System.out.println("Dealers cards: ");
                    while (totalValueDealer < 17) {
                        dealer.getCards().add(cards.getCards().get(rand.nextInt(52)));
                        writeDeck(dealer.getCards());
                        System.out.println(" ");
                        for (int i = 0; i < dealer.getCards().size(); i++) {
                            totalValueDealer = totalValueDealer + dealer.getCards().get(i).getValue_in_blackjack();
                        }
                    }
                }
                System.out.println(" ");


                //Possibilities of the game ending.
                if (totalValueDealer>21){
                    System.out.println("Dealer busted!");
                    if (!checkIfUserBusted(user)){
                        int payout = (bet*3)/2;
                        user.setMoney(user.getMoney()+payout);
                    }
                }


                for (int i = 0; i < user.getCards().size(); i++) {
                    totalValueUser = totalValueUser + user.getCards().get(i).getValue_in_blackjack();
                }

                if (totalValueDealer<totalValueUser&&!checkIfUserBusted(user)){
                    System.out.println("You beat the dealer!");
                    int payout = (bet*3)/2;
                    user.setMoney(user.getMoney()+payout);
                }else {
                    user.setMoney(user.getMoney()-bet);
                    System.out.println("You lost to the dealer!");
                }


                endOfGame = true;
            }
        }
    }

    //Bots either draw a card or stay depending on their deck value.
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

    //Check if users deck value is over 21.
    private boolean checkIfUserBusted(User user){
        int totalValue = 0;
        for (int i = 0; i < user.getCards().size(); i++) {
            totalValue = totalValue + user.getCards().get(i).getValue_in_blackjack();
        }
        return totalValue > 21;
    }

    //Check if bots deck value is over 21.
    private boolean checkIfBotBusted(Bot bot){
        int totalValue = 0;
        for (int i = 0; i < bot.getCards().size(); i++) {
            totalValue = totalValue + bot.getCards().get(i).getValue_in_blackjack();
        }
        return totalValue > 21;
    }

    //write out the cards in the deck.
    private void writeDeck(ArrayList<Cards> cards){
        for (Cards card : cards) {
            System.out.println("(" + card.getFace() + " "
                    + card.getSuit() + " "
                    + card.getValue_in_blackjack() + ") ");
        }
        System.out.println(" ");
    }
}
