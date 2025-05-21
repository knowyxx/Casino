package Commands;

import Games.Blackjack.Blackjack;
import Games.Poker.Poker;
import Games.Roulette.Roulette;
import Games.Slots.Slots;
import Players.User;

import java.util.Scanner;

public class Play implements Commands {

    Scanner sc = new Scanner(System.in);
    private int answer = 0;
    private boolean correct = false;
    private final Blackjack blackjack = new Blackjack();
    private final Poker poker = new Poker();
    private final Roulette roulette = new Roulette();
    private final Slots slots = new Slots();
    private User user;
    private int bet;

    @Override
    public void setUser(User setUser) {
        user = setUser;
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public String excecute() {

        answer = 0;
        System.out.println("""
                Which games would you like to play?
                1) Blackjack
                2) Poker
                3) Roulette
                4) Slots""");
//        while (!correct) {
//            try {
//                answer = sc.nextInt();
//                sc.nextLine();
//                correct = true;
//            } catch (Exception e) {
//                sc.nextLine();
//                System.out.println("Please enter a valid number");
//            }
//
//        }
//        correct = false;
        choosingGame();

        while (!correct) {
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
                                    user.winnings(-bet);
                                    correct = true;
                                }else {
                                    System.out.println("Insufficient amounts.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                    blackjack.blackjackGame(user, bet, answer);
                    break;

                case 2:
                    while(!correct){
                        System.out.println("How many bots would you like to play? ");
                        try {
                            answer = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                    poker.pokerGame(user, answer);
                    break;
                case 3:
                    while(!correct){
                        System.out.println("How much would you like to bet? (must be divisible by 5)");
                        try {
                            bet = sc.nextInt();
                            if (bet <= user.getMoney() && bet % 5 == 0){
                                user.winnings(-bet);
                                correct = true;
                            }else {
                                System.out.println("Insufficient amounts.");
                            }
                        } catch (Exception e) {
                            System.out.println("Please enter a valid number.");;
                        }
                    }
                    roulette.rouletteGame(user, bet);
                    break;
                case 4:
                    try {
                        slots.slotsGame(user);
                        correct = true;
                        break;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    
                default:

                    System.out.println("No valid choice");
                    choosingGame();
                    break;
            }
        }
        correct = false;
        return "";
    }

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
}
