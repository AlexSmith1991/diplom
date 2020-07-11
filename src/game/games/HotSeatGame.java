package game.games;

import game.ChipColor;
import game.player.HumanPlayer;

public class HotSeatGame{
    public void start(){
        HumanPlayer playerWhite = new HumanPlayer(ChipColor.WHITE);
        HumanPlayer playerBlack = new HumanPlayer(ChipColor.BLACK);
        LocalGame localGame = new LocalGame(playerWhite, playerBlack);
        localGame.start();
    }
}
