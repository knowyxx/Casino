package Bar;

import java.util.HashMap;

public class Bar {
    private String bartender="Hubert";
    private HashMap<Drinks, Integer> drinks;

    public boolean loadDrinks(){
        drinks = new HashMap<>();
        drinks.put(Drinks.NEGRONI,8);
        drinks.put(Drinks.SPRITZ,4);
        drinks.put(Drinks.REDBULLVODKA,1);
        drinks.put(Drinks.LONGISLANDICEDTEA,10);
        drinks.put(Drinks.SEXONTHEBEACH,2);
        drinks.put(Drinks.OLDFASHIONED,2);
        drinks.put(Drinks.MARGARITA,1);
        drinks.put(Drinks.COSMOPOLITAN,3);
        drinks.put(Drinks.MARTINI,1);
        drinks.put(Drinks.MOJITO,0);
        drinks.put(Drinks.ONEMILIONBEERS,99);
        drinks.put(Drinks.BEERANDCARKEYS,10);
        return true;
    }
}
