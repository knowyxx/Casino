package UNIT_tests;

import Cards.Cards;
import Games.Blackjack.Blackjack;
import Players.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class UserDrawCardsTest {

    @Test
    public void testDrawCards() {
        User user = new User();
        Blackjack blackjack = new Blackjack();
        Cards cards = new Cards();
        Random rand = new Random();
        cards.loadCards();
        user.loadCards();
        user.getCards().add(cards.getCards().get(rand.nextInt(52)));
        blackjack.writeDeck(user.getCards());
        assertEquals(1, user.getCards().size());
    }
}
