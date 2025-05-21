package Commands;

import Bar.Bar;
import Players.User;

import java.util.Scanner;

public class GoToTheBar implements Commands{
    private User user;
    private Bar bar = new Bar();
    private Scanner sc = new Scanner(System.in);


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
        bar.loadDrinks();
        System.out.println("""
            Welcome to the bar!
            What kind of drink would you like to buy?
        """);
        String answer = "";
        for (int i = 0; i < bar.getDrinks().size()-2; i++) {
            System.out.println(bar.getDrinks().get(i).getName() + " -=- " + bar.getDrinks().get(i).getPrice());
        }
        try {
            answer = sc.next();
            answer = answer.toUpperCase();
            answer = answer.replace(" ","");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (answer.equals("NAME")) {
            return "The bartenders' name is " + bar.getBartender() + " or Hubie for short.";
        }

        for (int i = 0; i < bar.getDrinks().size(); i++) {
            if (answer.equals(bar.getDrinks().get(i).getDrink().toString())) {
                user.drinkDrink(bar.getDrinks().get(i).getLuck());
                return "Wow you just drank " + answer + " and you feel kinda lucky all of the sudden.";
            }
        }

        return "chybka GoToTheBar";
    }
}
