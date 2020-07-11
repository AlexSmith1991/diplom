package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameField {
    final private ArrayList<FieldCell> fieldCells;

    private int outWhite;
    private int outBlack;
    private ChipColor color;
    private boolean isFirstStep;

    public GameField(ChipColor color) {
        this.color = color;
        fieldCells = new ArrayList<>(24);
        for (int i = 0; i < 24; i++) {
            fieldCells.add(new FieldCell(color));
        }
        outWhite = 0;
        outBlack = 0;
        isFirstStep = true;
    }

    public ArrayList<FieldCell> getFieldCells() {
        return fieldCells;
    }

    public boolean makeSteps(List<Step> steps){
        for (Step step: steps){
            ChipColor chipColor = fieldCells.get(step.getFrom()).getColor();
            fieldCells.get(step.getFrom()).takeChip();
            if (step.getTo() == 0){
                if (chipColor == ChipColor.BLACK){
                    if (++outBlack == 15){
                        return true;
                    }
                } else {
                    if (++outWhite == 15){
                        return true;
                    }
                }
            } else {
                fieldCells.get(step.getTo()).addChip(color);
            }
        }
        return false;
    }

    public List<Integer> getAvailableFrom(int dice1, int dice2){
        Set<Integer> values = new HashSet<>();
        for (int i = 0; i < 24; i++) {
            if (fieldCells.get(i).getColor() == color &&
                    (fieldCells.get(i + dice1).isAvailable(color) || fieldCells.get(i + dice2).isAvailable(color))){
                values.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(values);
        return result;
    }

    private List<Integer> getAvailableToSame(int from, int dice, int size){
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            int to = from + (dice * i);
            if (fieldCells.get(to).isAvailable(color)){
                result.add(to);
            }
        }
        return result;
    }

    public List<Integer> getAvailableToDifferent(int from, int dice1, int dice2){
        List<Integer> result = new ArrayList<>();
        Set<Integer> values = new HashSet<>();
        int to = from + dice1;
        if (fieldCells.get(to).isAvailable(color)){
            values.add(to);
            to =+ dice2;
            if (fieldCells.get(to).isAvailable(color)){
                values.add(to);
            }
        }
        to = from + dice2;
        if (fieldCells.get(to).isAvailable(color)){
            values.add(to);
            to =+ dice1;
            if (fieldCells.get(to).isAvailable(color)){
                values.add(to);
            }
        }
        result.addAll(values);
        return result;
    }

    public List<Integer> getAvailableTo(int from, List<Integer> dices){
        List<Integer> result = new ArrayList<>();
        int size = dices.size();
        int to;
        if (size == 1){
            to = from + dices.get(0);
            if (fieldCells.get(to).isAvailable(color)){
                result.add(to);
            }
        } else {
            if (dices.get(0) == dices.get(1)){
                result = this.getAvailableToSame(from, dices.get(0), size);
            } else {
                result = this.getAvailableToDifferent(from, dices.get(0), dices.get(1));
            }
        }
        return result;
    }
}
