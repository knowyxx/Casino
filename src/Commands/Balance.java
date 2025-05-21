package Commands;

import Players.User;

public class Balance implements Commands{
    private User user;

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
        return "Your balance: " + user.getMoney();
    }
}
