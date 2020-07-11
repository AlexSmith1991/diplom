package game.player;

import game.ChipColor;
import game.Step;

import java.util.List;

public class ComputerPlayer extends LocalPlayer {

    public ComputerPlayer(ChipColor chipColor) {
        super(chipColor);
    }

    @Override
    public List<Step> nextSteps() {
        return null;
    }

    @Override
    public void win() {

    }

    @Override
    public void lose() {

    }
}
