package Games.Slots;

import Players.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class for slots.
 */
public class Slots {

    private boolean endOfGame;
    private final Scanner sc =  new Scanner(System.in);
    private final Random rand = new Random();
    private final ArrayList<String> symbol = new ArrayList<>();
    private final ArrayList<String> slot = new ArrayList<>();

    /**
     * Main method for slot game.
     */
    public void slotsGame(User user){
        /*
            Loading symbols and welcoming the user.
         */
        loadSymbol();
        System.out.println("""
                Welcome to the game of slots!
                How much money would you like to spend per spin?
                """);
        int bet=0;
        boolean correct = false;
        while (!correct){
            try {
                bet = sc.nextInt();
                correct = true;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Please enter a valid number!");
            }
        }


        /*
            User can spam spinning until he wants to exit or doesn't have any money.
         */
        while (!endOfGame) {
            System.out.println("""
                    would you like to spin or end?
                    s for spin
                    end for end of game
                    """);
            String answer = sc.next();
            if (answer.equalsIgnoreCase("s")) {
                int luck = rand.nextInt(300) + 1;
                if (user.getLuck() >= luck) {
                    luck = rand.nextInt(7);
                    for (int i = 0; i < 3; i++) {
                        System.out.print(symbol.get(luck));
                    }
                    System.out.println(" ");
                    System.out.println("You win!");
                    user.winnings(bet *343);
                }
                if (user.getLuck() < luck){
                    slot.clear();
                    for (int i = 0; i < 3; i++) {
                        slot.add(symbol.get(rand.nextInt(7)));
                    }
                    for (String s : slot) {
                        System.out.print(s);
                    }
                    if (slot.get(0).equals(slot.get(1)) && slot.get(1).equals(slot.get(2))) {
                        System.out.println("You win!");
                        user.winnings(bet *343);
                    }else {
                        System.out.println("You lose!");
                        user.winnings(-bet);
                    }
                }
            }
            if (answer.equalsIgnoreCase("end") || user.getMoney() <= 0) {
                endOfGame = true;
            }
        }
    }

    /**
     * Clears and loads symbols into ArrayList.
     */
    private void loadSymbol(){
        symbol.clear();
        symbol.add("\uD83C\uDF49");
        symbol.add("\uD83C\uDF52");
        symbol.add("\uD83C\uDF53");
        symbol.add("\uD83C\uDF47");
        symbol.add("\uD83C\uDF46");
        symbol.add("\uD83C\uDF4D");
        symbol.add("\uD83C\uDF4C");
    }
}
