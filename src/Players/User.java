package Players;

import Cards.Cards;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Class for the user.
 */
public class User {
    private int money = 300;
    private double luck = 1;
    private String age;
    private ArrayList<Cards> cards;


    /**
     * Load the user practically a constructor but modified to ask how old the user is before entering the casino.
     */
    public void loadUser(){
        Scanner sc = new Scanner(System.in);
        cards = new ArrayList<Cards>();
        if (age == null){
            System.out.println("How old are you?");
            boolean correct = false;
            while(!correct){
                try {
                    setAge(sc.next());
                    if (Integer.parseInt(age) < 100 && Integer.parseInt(age) > 0){
                        correct = true;

                    }else {
                        System.out.println("Invalid age.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid age.");
                }
            }
        }

    }

    public void loadCards(){
        cards = new ArrayList<Cards>();
    }

    /**
     * Setters and getters.
     */
    public void addLuck(int luck){
        this.luck += luck;
    }

    public void winnings(double bet){
        money += Math.toIntExact(Math.round(bet));
    }

    public int getMoney() {
        return money;
    }

    public double getLuck() {
        return luck;
    }

    public String getAge() {
        return age;
    }

    /**
     * Regex used for setting users age to not have triple digits.
     */
    public void setAge(String age) {
        if (age.matches("\\d{1,2}")){
            this.age = age;
        }else {
            throw new IllegalArgumentException("Age is not valid");
        }

    }

    public ArrayList<Cards> getCards() {
        return cards;
    }
}
