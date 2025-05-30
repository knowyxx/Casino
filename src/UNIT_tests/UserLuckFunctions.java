package UNIT_tests;

import Commands.GoToTheBar;
import Players.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserLuckFunctions {

    @Test
    public void testAddLuck() {
        User user = new User();
        GoToTheBar goToTheBar = new GoToTheBar();
        goToTheBar.setUser(user);
        goToTheBar.drinkDrink(4);
        assertEquals(3, user.getLuck());
    }


}
