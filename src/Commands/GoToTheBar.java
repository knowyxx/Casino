package Commands;

import Bar.Bar;
import Players.User;

import java.util.Scanner;

/**
 * Command to go to the bar.
 */
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

    /**
     * The bar writes out the name of the drinks and the price, then it loops until the user wants to either exit
     * the bar or if they choose a drink they can afford.
     */
    @Override
    public String excecute() {
        bar.loadDrinks();
        System.out.println("""
            Welcome to the bar!
            What kind of drink would you like to buy?
        """);


        System.out.println(1 + ") " + "exit");
        for (int i = 0; i < bar.getDrinks().size()-2; i++) {
            System.out.println(i + 2 + ") " + bar.getDrinks().get(i).getName() + " -=- " + bar.getDrinks().get(i).getPrice());
        }



        boolean correct = false;

        while (!correct) {
            int answer = 100;
            try {
                answer = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (answer == 1) {
                return "aight";
            }
            if (answer - 2 <= bar.getDrinks().size()){
                if (user.getMoney() >= bar.getDrinks().get(answer - 2).getPrice()){
                    drinkDrink(answer - 2);
//                    user.winnings(-bar.getDrinks().get(answer - 2).getPrice());
//                    user.addLuck(bar.getDrinks().get(answer - 2).getLuck());
                    return "Wow you just drank " + bar.getDrinks().get(answer - 2).getName() + " and you feel kinda lucky all of the sudden.";
                }else {
                    System.out.println("You don't have enough money to buy this drink.");
                }
            }else {
                System.out.println("Wrong number, please try again.");
            }
        }

        return "chybka GoToTheBar";
    }

    public void drinkDrink(int drink){
        bar.loadDrinks();
        user.winnings(-bar.getDrinks().get(drink).getPrice());
        user.addLuck(bar.getDrinks().get(drink).getLuck());
    }

}
