package game;

import java.util.Random;

public class GameDice {
    public static int diceThrow(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
