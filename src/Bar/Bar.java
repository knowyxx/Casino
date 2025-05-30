package Bar;

import java.util.ArrayList;

/**
 * Class for the bar.
 */
public class Bar {
    /**
     * Variables for the bar, bartenders' name is an Easter egg.
     */
    private String bartender="Hubert";
    private String name;
    private int price;
    private Drinks drink;
    private int luck;
    private ArrayList<Bar> drinks;

    /**
     * Constructors for the class.
     */
    public Bar(String name, int price, Drinks drink, int luck) {
        this.name = name;
        this.price = price;
        this.drink = drink;
        this.luck = luck;
    }

    public Bar() {
    }


    /**
     * Method to load the drinks into an ArrayList, where we use it further.
     */
    public void loadDrinks(){
        drinks = new ArrayList<Bar>();
        drinks.add(new Bar("Negroni",1000, Drinks.NEGRONI, 8));
        drinks.add(new Bar("Spritz", 700, Drinks.SPRITZ, 4));
        drinks.add(new Bar("Red bull Vodka" ,100, Drinks.REDBULLVODKA, 1));
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


    /**
     * Setters and getters.
     */
    public int getPrice() {
        return price;
    }

    public int getLuck() {
        return luck;
    }

    public ArrayList<Bar> getDrinks() {
        return drinks;
    }

    public String getName() {
        return name;
    }
}
