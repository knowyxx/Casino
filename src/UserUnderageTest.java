import Players.User;
import org.junit.jupiter.api.Test;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserUnderageTest {

    @Test
    public void testUnderageUserShowsWarning() {
        String simulatedInput = "exit\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        Console console = new Console();
        console.getUser().setAge("12");
        console.initialization();
        console.start();
        System.setIn(System.in);
        System.setOut(System.out);
        String output = testOut.toString();
        assertTrue(output.contains("You are not old enough to go to a casino"));
    }

}