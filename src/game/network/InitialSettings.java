package game.network;

import game.ChipColor;

import java.io.Serializable;

public class InitialSettings implements Serializable {
    private ChipColor color;
    private int whiteDice;
    private int blackDice;

    public InitialSettings(ChipColor color, int whiteDice, int blackDice) {
        this.color = color;
        this.whiteDice = whiteDice;
        this.blackDice = blackDice;
    }
}
