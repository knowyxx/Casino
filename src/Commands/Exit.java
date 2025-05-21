package Commands;

import Players.User;

public class Exit implements Commands {

    @Override
    public void setUser(User user) {

    }

    @Override
    public boolean exit() {
        return true;
    }

    @Override
    public String excecute() {
        return "Alrite brudda";
    }
}
