package Commands;

import Games.Blackjack.Blackjack;
import Games.Poker.Poker;
import Games.Roulette.Roulette;
import Games.Slots.Slots;
import Players.User;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Command that starts each game.
 */
public class Play implements Commands {

    Scanner sc = new Scanner(System.in);
    private int answer = 0;
    private boolean correct = false;
    private final Blackjack blackjack = new Blackjack();
    private final Roulette roulette = new Roulette();
    private final Slots slots = new Slots();
    private User user;
    private final Poker poker = new Poker();

    @Override
    public void setUser(User setUser) {
        user = setUser;
    }

    @Override
    public boolean exit() {
        return false;
    }

    /**
     * The User chooses which game he wants to play and then answer further to other questions.
     */
    @Override
    public String excecute() {

        answer = 0;
        System.out.println("""
                Which games would you like to play?
                1) Blackjack
                2) Poker
                3) Roulette
                4) Slots""");
        choosingGame();

        int bet;
        switch (answer) {
            case 1:
                while(!correct){
                    System.out.println("How many bots would you like to play? (max number of bots = 4)");
                    try {
                        answer = sc.nextInt();
                        if (answer < 5 && answer > -1) {
                            System.out.println("And how much would you like to bet? (must be divisible by 10)");

                            bet = sc.nextInt();

                            if (bet <= user.getMoney() && bet % 10 == 0){
                                correct = true;
                                blackjack.blackjackGame(user, bet, answer);
                            }else {
                                System.out.println("Insufficient amounts.");
                            }
                        }
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Please enter a valid number.");
                    }
                }
                break;

            case 2:
                while(!correct){

                    try {

                        correct = true;
                        poker.pokerGame(user, 4);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case 3:
                while(!correct){
                    System.out.println("How much would you like to bet? (must be divisible by 5)");
                    try {
                        bet = sc.nextInt();
                        if (bet <= user.getMoney() && bet % 5 == 0){
                            //user.winnings(-bet);
                            correct = true;
                            roulette.rouletteGame(user, bet);
                        }else {
                            System.out.println("Insufficient amounts.");
                        }
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Please enter a valid number.");;
                    }
                }
                break;
            case 4:
                try {
                    slots.slotsGame(user);
                    break;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            default:

                System.out.println("No valid choice, try again.");
                break;

        }
        correct = false;
        return "";
    }

    /**
     * Method that asks the user which game he wants to play and loops until right input.
     */
    public void choosingGame(){
        while (!correct) {
            try {
                answer = sc.nextInt();
                sc.nextLine();
                correct = true;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Please enter a valid number");
            }

        }
        correct = false;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
