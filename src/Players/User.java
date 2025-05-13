package Players;

import Bar.Drinks;
import Cards.Cards;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private double money = 200;
    private double luck = 1;
    private Drinks drinks;
    private int age;
    private ArrayList<Cards> cards;
    private boolean bustBlackjack;
    private boolean foldPoker;
    private boolean matchingHighestBetPoker;


    public boolean loadUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("How old are you?");
        setAge(sc.nextInt());
        return true;
    }

    public void winnings(double bet){
        money = money + bet;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Cards> cards) {
        this.cards = cards;
    }

    public boolean isBustBlackjack() {
        return bustBlackjack;
    }

    public void setBustBlackjack(boolean bustBlackjack) {
        this.bustBlackjack = bustBlackjack;
    }

    public boolean isFoldPoker() {
        return foldPoker;
    }

    public void setFoldPoker(boolean foldPoker) {
        this.foldPoker = foldPoker;
    }

    public boolean isMatchingHighestBetPoker() {
        return matchingHighestBetPoker;
    }

    public void setMatchingHighestBetPoker(boolean matchingHighestBetPoker) {
        this.matchingHighestBetPoker = matchingHighestBetPoker;
    }
}
