package Commands;

import Players.User;

public interface Commands {
    void setUser(User setUser);
    boolean exit();
    void excecute();
}
