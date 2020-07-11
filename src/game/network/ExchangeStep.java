package game.network;

import game.Step;

import java.io.Serializable;
import java.util.List;

public class ExchangeStep implements Serializable {
    private int dice1;
    private int dice2;
    private List<Step> steps;

    public ExchangeStep(int dice1, int dice2, List<Step> steps) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.steps = steps;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
