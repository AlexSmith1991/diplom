package game.player;

import game.Step;

import java.util.List;

public interface Player {
    public List<Step> nextSteps();
    public boolean makeSteps(List<Step> steps);
    public void win();
    public void lose();
}
