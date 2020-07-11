package game.games;

import game.GameDice;
import game.Step;
import game.player.LocalPlayer;

import java.util.List;

public class LocalGame implements Game {
    private LocalPlayer player1;
    private LocalPlayer player2;

    public LocalGame(LocalPlayer playerWhite, LocalPlayer playerBlack) {
        int diceWhite = 0;
        int diceBlack = 0;
        while (diceWhite == diceBlack){
            diceWhite = GameDice.diceThrow();
            diceBlack = GameDice.diceThrow();
        }
        if (diceWhite > diceBlack){
            this.player1 = playerWhite;
            this.player2 = playerBlack;
        } else {
            this.player1 = playerBlack;
            this.player2 = playerWhite;
        }
    }

    @Override
    public void start() {
        while (true){
            List<Step> nextSteps = player1.nextSteps();
            if (player2.makeSteps(nextSteps)){
                player1.win();
                player2.lose();
                break;
            }
            nextSteps = player2.nextSteps();
            if (player1.makeSteps(nextSteps)){
                player2.win();
                player1.lose();
                break;
            }
        }
    }
}
