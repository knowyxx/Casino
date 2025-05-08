import Commands.Commands;
import Commands.Play;
import Commands.Exit;
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
        user.loadUser();
    }

    public String doCommand(){
        System.out.println(">>");
        command = sc.nextLine();
        command = command.trim();
        command = command.toLowerCase();
        if (commandsHashMap.containsKey(command)){
            commandsHashMap.get(command).setUser(user);
            if (user.getAge()<18){
                commandsHashMap.get("exit").exit();
                return "You are not old enough to go to a casino";
            }
            System.out.println(commandsHashMap.get(command));
            exit = commandsHashMap.get(command).exit();
            return command;
        }else return "Invalid command";
    }

    public void start(){
        initialization();
        try {
            do {
                doCommand();
            }while (!exit);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
