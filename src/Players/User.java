package Players;

import Bar.Drinks;
import Cards.Cards;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private int money = 200;
    private double luck = 1;
    private Drinks drinks;
    //private String age = "18";
    private String age;
    private ArrayList<Cards> cards;


    public void loadUser(){
        Scanner sc = new Scanner(System.in);
        cards = new ArrayList<Cards>();
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

    public void drinkDrink(int drinkLuck){
        if (drinks != null){
            setLuck(1 + drinkLuck);
        }
    }

    public void winnings(double bet){
        double throwaway = 0;
        throwaway = money + bet;
        money = (int) Math.round(throwaway);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public double getLuck() {
        return luck;
    }

    public void setLuck(double luck) {
        this.luck = luck;
    }

    public Drinks getDrinks() {

        return drinks;
    }

    public void setDrinks(Drinks drinks) {
        this.drinks = drinks;
    }

    public String getAge() {
        return age;
    }

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
