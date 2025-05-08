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
    private Blackjack blackjack = new Blackjack();
    private Poker poker = new Poker();
    private Roulette roulette = new Roulette();
    private Slots slots = new Slots();
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
    public void excecute() {

        System.out.println("Which games would you like to play?"+"\n"+
                "1) Blackjack"+"\n"+
                "2) Poker"+"\n"+
                "3) Roulette"+"\n"+
                "4) Slots");
        while (!correct) {
            try {
                answer = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid number");
            }
            correct = true;
        }
        correct = false;

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
                    correct = true;
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
                            throw new RuntimeException(e);
                        }
                    }
                    roulette.rouletteGame(user, bet);
                    break;
                case 4:
                    correct = true;
                default:
                    System.out.println("No valid choice");
            }

        }
    }
}
