import Commands.Commands;
import Commands.Play;
import Commands.Exit;
import Commands.GoToTheBar;
import Commands.Help;
import Commands.Balance;
import Players.User;

import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private HashMap<String, Commands> commandsHashMap = new HashMap<>();
    private User user = new User();
    private String command = "";
    private Scanner sc = new Scanner(System.in);
    private boolean exit = false;



    public void initialization(){
        commandsHashMap.put("play", new Play());
        commandsHashMap.put("exit", new Exit());
        commandsHashMap.put("gotothebar", new GoToTheBar());
        commandsHashMap.put("help", new Help());
        commandsHashMap.put("balance", new Balance());
        user.loadUser();
    }

    public void doCommand(boolean underage){
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
        }else {
            System.out.println("Invalid command");
        }
    }

    public void start(){
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

}
