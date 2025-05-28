package Commands;

import Players.User;

/**
 * Interface for commands.
 */
public interface Commands {
    void setUser(User setUser);
    boolean exit();
    String excecute();
}
