package game.player;

import game.ChipColor;
import game.GameField;
import game.Step;

import java.util.List;

public abstract class LocalPlayer implements Player{
    private ChipColor chipColor;
    private final GameField gameField;

    public LocalPlayer(ChipColor chipColor) {
        this.chipColor = chipColor;
        this.gameField = new GameField(chipColor);
    }

    public boolean makeSteps(List<Step> steps){
        return gameField.makeSteps(steps);
    };
}
