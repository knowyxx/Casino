package Games.Roulette;

import Cards.Cards;
import Players.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Roulette {
    private int number;
    private String color;
    private String parity;
    private String range;
    private ArrayList<Roulette> rouletteArrayList = new ArrayList<>();
    private int answerInt = 0;
    private String answerStr = "";
    private int luck = 0;

    public Roulette() {
    }

    public Roulette(int number, String color, String parity, String range) {
        this.number = number;
        this.color = color;
        this.parity = parity;
        this.range = range;
    }

    public boolean loadRoulette() {
        try (BufferedReader br = new BufferedReader(new FileReader("Roulette.csv"))) {
            String line;
            br.readLine();
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
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void rouletteGame(User user, int bet){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        boolean correct = false;
        int random = 0;
        System.out.println("""
                On what do you want to bet?
                1) number (0-36)
                2) color (red, black, green)
                3) parity (odd, even)
                4) range (low, high)
                """);
        while (!correct){
            try {
                answerInt = sc.nextInt();
                correct = true;
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        correct = false;

        switch (answerInt){
            case 1:
                System.out.println("What number would you like to bet on?");
                while (!correct){
                    try {
                        answerInt = sc.nextInt();
                        if (answerInt < 38 && answerInt > -1){
                            luck = rand.nextInt(100) + 1;
                            if (luck <= user.getLuck()){
                                System.out.println("You picked the right number, you won!  Your luck = " + user.getLuck());
                                user.winnings(bet * 37);
                            }else {
                                if (answerInt == rouletteArrayList.get(rand.nextInt(rouletteArrayList.size())).getNumber()) {
                                    System.out.println("You picked the right number, you won!");
                                    user.winnings(bet * 37);
                                } else {
                                    System.out.println("You picked the wrong number, you lose!");
                                    user.winnings((double) bet / 37);
                                }
                            }
                            correct = true;
                        }else {
                            System.out.println("Please pick a number between 1-37.");
                        }

                    }catch (InputMismatchException e){
                        System.out.println("Please pick a valid number.");
                    }
                }
            case 2:
                System.out.println("What color would you like to bet on?");
                while (!correct){
                    try {
                        answerStr = sc.next();
                        if (
                            answerStr.equalsIgnoreCase("red") ||
                            answerStr.equalsIgnoreCase("black") ||
                            answerStr.equalsIgnoreCase("green")
                        ){
                            luck = rand.nextInt(100) + 1;
                            if (luck <= user.getLuck()){
                                System.out.println("You picked the right color, you won!  Your luck = " + user.getLuck());
                                user.winnings(bet*37);
                            }else {
                                random = rand.nextInt(rouletteArrayList.size());
                                if (answerStr.equalsIgnoreCase(rouletteArrayList.get(random).getColor())) {
                                    if (rouletteArrayList.get(random).getColor().equalsIgnoreCase("green")) {
                                        user.winnings(bet * 37);
                                    }
                                    System.out.println("You picked the right color, you won!");
                                    user.winnings(bet * 2.18);
                                } else {
                                    System.out.println("You picked the wrong color, you lose!");
                                    user.winnings(bet / 2.18);
                                }
                            }
                            correct = true;
                        }else {
                            System.out.println("Please pick a valid color.");
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            case 3:
                System.out.println("What parity would you like to bet on?");
                while (!correct){
                    try {
                        answerStr = sc.next();
                        if (
                                answerStr.equalsIgnoreCase("odd") ||
                                        answerStr.equalsIgnoreCase("even")
                        ){
                            do {
                                random = rand.nextInt(rouletteArrayList.size());
                            } while (random == 0);

                            luck = rand.nextInt(100) + 1;
                            if (luck <= user.getLuck()){
                                System.out.println("You picked the right parity, you won!  Your luck = " + user.getLuck());
                                user.winnings(bet*37);
                            }
                            if (answerStr.equalsIgnoreCase(rouletteArrayList.get(rand.nextInt(rouletteArrayList.size())).getParity())){
                                System.out.println("You picked the right parity, you won!");
                                user.winnings(bet*2);
                            }else {
                                System.out.println("You picked the wrong parity, you lose!");
                                user.winnings((double) bet /2);
                            }
                            correct = true;
                        }else {
                            System.out.println("Please pick a valid parity.");
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            case 4:
                System.out.println("What range would you like to bet on?");
                while (!correct){
                    try {
                        answerStr = sc.next();
                        if (
                                answerStr.equalsIgnoreCase("low") ||
                                        answerStr.equalsIgnoreCase("high")
                        ){
                            do {
                                random = rand.nextInt(rouletteArrayList.size());
                            } while (random == 0);

                            luck = rand.nextInt(100) + 1;
                            if (luck <= user.getLuck()){
                                System.out.println("You picked the right range, you won!  Your luck = " + user.getLuck());
                                user.winnings(bet*37);
                            }
                            if (answerStr.equalsIgnoreCase(rouletteArrayList.get(rand.nextInt(rouletteArrayList.size())).getRange())){
                                System.out.println("You picked the right range, you won!");
                                user.winnings(bet*2);
                            }else {
                                System.out.println("You picked the wrong range, you lose!");
                                user.winnings((double) bet /2);
                            }
                            correct = true;
                        }else {
                            System.out.println("Please pick a valid range.");
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getParity() {
        return parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public ArrayList<Roulette> getRouletteArrayList() {
        return rouletteArrayList;
    }

    public void setRouletteArrayList(ArrayList<Roulette> rouletteArrayList) {
        this.rouletteArrayList = rouletteArrayList;
    }
}
