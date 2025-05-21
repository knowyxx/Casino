package Bar;

import Players.User;

import java.util.ArrayList;

public class Bar {
    private String bartender="Hubert";
    private String name;
    private int price;
    private Drinks drink;
    private int luck;
    private ArrayList<Bar> drinks;

    public Bar(String name, int price, Drinks drink, int luck) {
        this.name = name;
        this.price = price;
        this.drink = drink;
        this.luck = luck;
    }

    public Bar() {
    }

    public void giveUserDrink(User user, Drinks drink) {
        for (Bar bar : drinks) {
            if (bar.getDrink() == drink) {
                user.setDrinks(drink);
                user.drinkDrink(bar.getLuck());
            }
        }
    }

    public void loadDrinks(){
        drinks = new ArrayList<Bar>();
        drinks.add(new Bar("Negroni",1000, Drinks.NEGRONI, 8));
        drinks.add(new Bar("Spritz", 700, Drinks.SPRITZ, 4));
        drinks.add(new Bar("Redbull Vodka" ,100, Drinks.REDBULLVODKA, 1));
        drinks.add(new Bar("Long Island Iced Tea", 1500, Drinks.LONGISLANDICEDTEA, 10));
        drinks.add(new Bar("Sex On The Beach", 300, Drinks.SEXONTHEBEACH, 2));
        drinks.add(new Bar("Old Fashioned", 250, Drinks.OLDFASHIONED, 2));
        drinks.add(new Bar("Margarita", 600, Drinks.MARGARITA, 1));
        drinks.add(new Bar("Cosmopolitan", 650, Drinks.COSMOPOLITAN, 3));
        drinks.add(new Bar("Martini", 400, Drinks.MARTINI, 1));
        drinks.add(new Bar("Mojito", 500, Drinks.MOJITO, 0));
        drinks.add(new Bar("One Milion Beers", 2000, Drinks.ONEMILIONBEERS, 99));
        drinks.add(new Bar("Beer And Car Keys", 500, Drinks.BEERANDCARKEYS, 10));
    }

    public String getBartender() {
        return bartender;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Drinks getDrink() {
        return drink;
    }

    public void setDrink(Drinks drink) {
        this.drink = drink;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public ArrayList<Bar> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Bar> drinks) {
        this.drinks = drinks;
    }

    public String getName() {
        return name;
    }
}
