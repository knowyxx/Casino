package Games.Poker;

import Cards.Cards;
import Players.Bot;
import Players.User;

import java.util.Random;
import java.util.Scanner;

public class PokerPlays {
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();
    private Cards cards = new Cards();
    private int throwaway = 0;
    private int bet = 0;
    private boolean correct;
    private User user;



    public void userBet(User user, int bet){
        System.out.printf("How much would you like to bet? ");
        while(!correct){
            try {
                throwaway = sc.nextInt();
                if (user.getMoney() <= throwaway){
                    bet = bet + throwaway;
                    user.winnings(-throwaway);
                    correct = true;
                    System.out.println("Your bet amount is: " + bet + ".");
                    System.out.println("You have: " + user.getMoney() + " money left.");
                }else {
                    System.out.println("You don't have enough money!");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void userCall(User user, int bet, boolean matchingHighestBet, int highestBet){
        if (!matchingHighestBet){
            if (user.getMoney() <= highestBet){
                throwaway = highestBet - bet;
                bet = bet + throwaway;
                user.winnings(-throwaway);
            }else {
                userFold(user);
            }
        }
    }

    public void userFold(User user){
        user.setFoldPoker(true);
    }


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

    public void checkForCombination(){
        
    }
}
