package UNIT_tests;
import Cards.Cards;
import Games.Roulette.Roulette;
import org.junit.jupiter.api.Test;

public class CardsAndRouletteLoadingTest {

    @Test
    public void cardsLoadingTest(){
        Cards cards = new Cards();
        cards.loadCards();
        assert(cards.getCards().size() == 52);
    }

    @Test
    public void rouletteLoadingTest(){
        Roulette roulette = new Roulette();
        roulette.loadRoulette();
        assert(roulette.getRouletteArrayList().size() == 37);
    }
}
