package Commands;

import Players.User;

/**
 * Command to write out all the commands.
 */
public class Help implements Commands{
    @Override
    public void setUser(User setUser) {

    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public String excecute() {
        return """
                exit - to leave the casino.
                >> play - to play any of the games presented.
                >> go to the bar - to go to the bar.
                >> help - to get all the commands.
                """;
    }
}
