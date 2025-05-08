import Cards.Cards;

import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Cards cards = new Cards();
        try {
            cards.loadCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            System.out.print(rand.nextInt(20)+" ");
        }



    }
}