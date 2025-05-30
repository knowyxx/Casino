package Games.Roulette;

import Players.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class for the roulette game.
 */
public class Roulette {
    private int number;
    private String color;
    private String parity;
    private String range;
    private final ArrayList<Roulette> rouletteArrayList = new ArrayList<>();
    private int answerInt = 0;

    /**
     * Constructors for class.
     */
    public Roulette() {}

    public Roulette(int number, String color, String parity, String range) {
        this.number = number;
        this.color = color;
        this.parity = parity;
        this.range = range;
    }

    /**
     * Loading roulette properties from Roulette.csv.
     */
    public void loadRoulette() {
        rouletteArrayList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("Roulette.csv"))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                Roulette roulette1 = new Roulette(
                        Integer.parseInt(split[0]),
                        split[1],
                        split[2],
                        split[3]
                );
                rouletteArrayList.add(roulette1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Main method for the roulette game.
     */
    public void rouletteGame(User user, int bet) {
        /*
            Resetting variables.
         */
        loadRoulette();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        boolean correct = false;
        int random;

        /*
            Welcoming the user.
         */
        System.out.println("""
                Welcome to roulette!
                On what do you want to bet?
                1) number (0-36)
                2) color (red, black, green)
                3) parity (odd, even)
                4) range (low, high)
                """);

        /*
            Looping scanner until right input.
         */
        while (!correct) {
            try {
                answerInt = Integer.parseInt(sc.nextLine());
                if (answerInt >= 1 && answerInt <= 4) {
                    correct = true;
                } else {
                    System.out.println("Please pick a valid number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        correct = false;
        String answerStr;
        int luck;

        /*
            User chose what he wants to bet on and wins depends on his luck.
         */
        switch (answerInt) {
            case 1:
                System.out.println("What number would you like to bet on?");
                while (!correct) {
                    try {
                        answerInt = Integer.parseInt(sc.nextLine());
                        if (answerInt >= 0 && answerInt <= 36) {
                            luck = rand.nextInt(100) + 1;
                            if (luck <= user.getLuck()) {
                                System.out.println("You picked the right number, you won!  Your luck = " + user.getLuck());
                                user.winnings((bet * 37) - bet);
                            } else {
                                if (answerInt == rouletteArrayList.get(rand.nextInt(rouletteArrayList.size())).getNumber()) {
                                    System.out.println("You picked the right number, you won!");
                                    user.winnings((bet * 37) - bet);
                                } else {
                                    System.out.println("You picked the wrong number, you lose!");
                                    user.winnings((double) (bet / 37) - bet);
                                }
                            }
                            correct = true;
                        } else {
                            System.out.println("Please pick a number between 0 and 36.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }
                break;

            case 2:
                System.out.println("What color would you like to bet on?");
                while (!correct) {
                    try {
                        answerStr = sc.nextLine().toLowerCase();
                        if (answerStr.equals("red") || answerStr.equals("black") || answerStr.equals("green")) {
                            random = rand.nextInt(rouletteArrayList.size());
                            luck = rand.nextInt(100) + 1;
                            if (luck <= user.getLuck()) {
                                System.out.println("You picked the right color, you won!  Your luck = " + user.getLuck());
                                user.winnings((bet * 37) - bet);
                            } else {
                                if (answerStr.equals(rouletteArrayList.get(random).getColor().toLowerCase())) {
                                    if (answerStr.equals("green")) {
                                        user.winnings((bet * 37) - bet);
                                    } else {
                                        user.winnings((bet * 2.18) - bet);
                                    }
                                    System.out.println("You picked the right color, you won!");
                                } else {
                                    System.out.println("You picked the wrong color, you lose!");
                                    user.winnings((bet / 2.18) - bet);
                                }
                            }
                            correct = true;
                        } else {
                            System.out.println("Please pick a valid color (red, black, green).");
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong. Try again.");
                    }
                }
                break;

            case 3:
                System.out.println("What parity would you like to bet on?");
                while (!correct) {
                    try {
                        answerStr = sc.nextLine().toLowerCase();
                        if (answerStr.equals("odd") || answerStr.equals("even")) {
                            do {
                                random = rand.nextInt(rouletteArrayList.size());
                            } while (rouletteArrayList.get(random).getNumber() == 0);

                            luck = rand.nextInt(100) + 1;
                            if (luck <= user.getLuck()) {
                                System.out.println("You picked the right parity, you won!");
                                user.winnings((bet * 2) - bet);
                            } else {
                                if (answerStr.equals(rouletteArrayList.get(random).getParity().toLowerCase())) {
                                    System.out.println("You picked the right parity, you won!");
                                    user.winnings((bet * 2) - bet);
                                } else {
                                    System.out.println("You picked the wrong parity, you lose!");
                                    user.winnings((double) (bet / 2) - bet);
                                }
                            }
                            correct = true;
                        } else {
                            System.out.println("Please pick a valid parity (odd, even).");
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong. Try again.");
                    }
                }
                break;

            case 4:
                System.out.println("What range would you like to bet on?");
                while (!correct) {
                    try {
                        answerStr = sc.nextLine().toLowerCase();
                        if (answerStr.equals("low") || answerStr.equals("high")) {
                            do {
                                random = rand.nextInt(rouletteArrayList.size());
                            } while (rouletteArrayList.get(random).getNumber() == 0);

                            luck = rand.nextInt(100) + 1;
                            if (luck <= user.getLuck()) {
                                System.out.println("You picked the right range, you won!  Your luck = " + user.getLuck());
                                user.winnings((bet * 2) - bet);
                            } else {
                                if (answerStr.equals(rouletteArrayList.get(random).getRange().toLowerCase())) {
                                    System.out.println("You picked the right range, you won!");
                                    user.winnings((bet * 2) - bet);
                                } else {
                                    System.out.println("You picked the wrong range, you lose!");
                                    user.winnings((double) (bet / 2) - bet);
                                }
                            }
                            correct = true;
                        } else {
                            System.out.println("Please pick a valid range (low, high).");
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong. Try again.");
                    }
                }
                break;
        }
    }

    /**
     * Setters and getters.
     */
    public int getNumber() { return number; }

    public String getColor() { return color; }

    public String getParity() { return parity; }

    public String getRange() { return range; }

    public ArrayList<Roulette> getRouletteArrayList() {
        return rouletteArrayList;
    }
}
