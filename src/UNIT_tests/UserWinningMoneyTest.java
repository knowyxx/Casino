package UNIT_tests;

import Players.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserWinningMoneyTest {

    @Test
    public void testUserWinningMoney() {
        User user = new User();
        user.winnings(200);
        assertEquals(500, user.getMoney());
    }
}
