import Commands.Commands;
import Commands.Play;
import Commands.Exit;
import Commands.GoToTheBar;
import Commands.Help;
import Commands.Balance;
import Players.User;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Main class to start everything and to be able to start commands.
 */
public class Console {
    private HashMap<String, Commands> commandsHashMap = new HashMap<>();
    public User user = new User();
    private String command = "";
    private final Scanner sc = new Scanner(System.in);
    private boolean exit = false;

    /**
     * Method to initialize commands and user.
     */
    public void initialization() {
        commandsHashMap.put("play", new Play());
        commandsHashMap.put("exit", new Exit());
        commandsHashMap.put("gotothebar", new GoToTheBar());
        commandsHashMap.put("help", new Help());
        commandsHashMap.put("balance", new Balance());
        user.loadUser();
    }

    /**
     *
     * Method to execute commands and an Easter egg.
     * User cannot execute any commands except exit if he is underage or doesn't have any money.
     * @param underage
     */
    public void doCommand(boolean underage){
        user.getCards().clear();
        System.out.println(">>");
        command = sc.nextLine();
        command = command.trim();
        command = command.toLowerCase();
        command = command.replace(" ", "");
        if (commandsHashMap.containsKey(command)){
            if (user.getMoney()<=0) {
                if (command.equals("exit")){
                    exit = true;
                    System.out.println(">> " + commandsHashMap.get(command).excecute());
                }else {
                    System.out.println("You are brok lmao, get a job.");
                }
            }else if (user.getMoney()>0){
                commandsHashMap.get(command).setUser(user);
                System.out.println(">> " + commandsHashMap.get(command).excecute());
                exit = commandsHashMap.get(command).exit();
            }
        } else if (underage) {
            if (command.equals("exit")){
                exit = true;
            }
            System.out.println("You are not old enough to go to a casino");
        }else if (command.equals("name")){
            System.out.println("The bartenders' name is Hubert or Hubie for short.");
        }else {
            System.out.println("Invalid command");
        }
    }

    /**
     * Start method to start the process and loops commands until the user exits.
     */
    public void start() {
        System.out.println("""
                Welcome to the biggest and best casino in the whole solar system!
                Here we have a bar that offers you popular drinks.
                We also have popular games such as blackjack, poker, roulette and slots, which you can play!
                Have fun and good luck (you will need it)!
                """);
        initialization();
        boolean underage;
        underage = Integer.parseInt(user.getAge()) < 18;
        if (underage){
            System.out.println( "You are not old enough to go to a casino");
        }
        try {
            do {
                doCommand(underage);
            }while (!exit);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Setter and getter for user.
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
