package Games.Poker;

import Cards.Cards;
import Players.Bot;
import Players.User;

import java.util.*;

/**
 * Class for checking combinations in poker.
 * Everything written here is by ChatGPT.
 */
public class PokerCombinations {

    /**
     * The Main method for checking is a deck has a combination and returns the power of the combination.
     */
    public int checkCombination(ArrayList<Cards> hand){


        Map<Integer, Integer> valueCount = new HashMap<>();
        Map<String, Integer> suitCount = new HashMap<>();
        ArrayList<Integer> values = new ArrayList<>();

        for (Cards card : hand) {
            valueCount.put(card.getValue_in_poker(), valueCount.getOrDefault(card.getValue_in_poker(), 0) + 1);
            suitCount.put(card.getSuit(), suitCount.getOrDefault(card.getSuit(), 0) + 1);
            values.add(card.getValue_in_poker());
        }

        Collections.sort(values);
        boolean isFlush = suitCount.containsValue(5);
        boolean isStraight = sequential(values);
        boolean isRoyal = values.equals(Arrays.asList(10, 11, 12, 13, 14));

        if (isFlush && isRoyal){
            return 10;
        }
        if (isFlush && isStraight){
            return 9;
        }
        if (hasOfAKind(valueCount, 4)){
            return 8;
        }
        if (hasFullHouse(valueCount)){
            return 7;
        }
        if (isFlush){
            return 6;
        }
        if (isStraight){
            return 5;
        }
        if (hasOfAKind(valueCount, 3)){
            return 4;
        }
        if (hasTwoPair(valueCount)){
            return 3;
        }
        if (hasOfAKind(valueCount, 2)){
            return 2;
        }

        return 1;
    }

    /**
     * Checks if who has the highest card.
     */
    public int checkIfHighestCard(User user, ArrayList<Bot> bots){
        ArrayList<Integer> values = new ArrayList<>();
        int throwaway = 0;
        int ID = 0;

        for (int i = 0; i < user.getCards().size(); i++) {
            if (user.getCards().get(i).getValue_in_poker() < throwaway){
                throwaway = user.getCards().get(i).getValue_in_poker();
            }
        }
        values.add(throwaway);
        throwaway = 0;

        for (Bot bot : bots) {
            for (int j = 0; j < bot.getCards().size(); j++) {
                if (throwaway > bot.getCards().get(j).getValue_in_poker()) {
                    throwaway = bot.getCards().get(j).getValue_in_poker();
                }
            }
            values.add(throwaway);
        }
        throwaway = 0;

        for (int i = 0; i < values.size() ; i++) {
            if (throwaway > values.get(i)){
                throwaway = values.get(i);
                ID = i;
            }
        }

        return ID;
    }


    /*
        Checks if the cards are sequential in a deck.
     */
    public boolean sequential (ArrayList<Integer> values){
        Set<Integer> unique = new HashSet<>();
        if (unique.size() != values.size()){
            return false;
        }

        List<Integer> sorted = new ArrayList<>(unique);
        Collections.sort(sorted);

        boolean straight = true;
        for (int j = 0; j < 4; j++) {
            if (sorted.get(j) + 1 != sorted.get(j + 1)){
                straight = false;
                break;
            }
        }
        if (straight){
            return true;
        }

        return unique.containsAll(Arrays.asList(14,2,3,4,5));
    }

    /*
        Methods to check combinations.
     */
    public boolean hasOfAKind(Map<Integer, Integer> valueCount, int target){
        for (int count : valueCount.values()){
            if (count == target){
                return true;
            }
        }
        return false;
    }

    public boolean hasFullHouse(Map<Integer, Integer> valueCount){
        boolean hasThree = false;
        boolean hasTwo = false;

        for (int count : valueCount.values()){
            if (count == 3){
                hasThree = true;
            }
            if (count == 2){
                hasTwo = true;
            }
        }
        return hasThree && hasTwo;
    }

    public boolean hasTwoPair(Map<Integer, Integer> valueCount){
        int pairs = 0;
        for (int count : valueCount.values()){
            if (count == 2){
                pairs++;
            }
        }
        return pairs == 2;
    }

}
