package Games.Poker;

import Players.Bot;
import Players.User;

import java.util.Random;

/**
 * Class for methods for the user and bots.
 */
public class PokerPlays {
    private final Random rand = new Random();
    private int throwaway = 0;
    private boolean userSetFold = false;


    /**
     * Methods for users bet, call and fold.
     */
    public void userBet(User user, int bet){
        try {
            if (user.getMoney() <= throwaway){
                bet = bet + throwaway;
                user.winnings(-throwaway);
                boolean correct = true;
                System.out.println("Your bet amount is: " + bet + ".");
                System.out.println("You have: " + user.getMoney() + " money left.");
            }else {
                System.out.println("You don't have enough money!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
            }

    }


    public void userCall(User user, int bet, boolean matchingHighestBet, int highestBet){
        if (!matchingHighestBet){
            if (user.getMoney() <= highestBet){
                throwaway = highestBet - bet;
                bet = bet + throwaway;
                user.winnings(-throwaway);
            }else {
                userFold();
            }
        }
    }


    public void userFold(){
        userSetFold = true;
    }


    /**
     * Method for bots bet, call and fold.
     */
    public void botBet(Bot bot, int bet){
        bot.setBet(bot.getBet() + (rand.nextInt(21)*10));
    }

    public void botCall(Bot bot, boolean matchingHighestBet, int highestBet){
        if (!matchingHighestBet){
            if (rand.nextInt(101) >= 20){
                highestBet = highestBet + (rand.nextInt(21)*10);
                bot.setMatchingHighestBetPoker(true);
            }
            else {
                botFold(bot);
            }
        }
    }

    public void botFold(Bot bot){
        bot.setFoldPoker(true);
    }

}
