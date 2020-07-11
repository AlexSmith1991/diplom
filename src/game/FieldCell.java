package game;

public class FieldCell {
    private ChipColor color;
    private int chips;

    public FieldCell(ChipColor color) {
        this.color = color;
        this.chips = 0;
    }

    public ChipColor getColor() {
        return color;
    }

    public void setColor(ChipColor color) {
        this.color = color;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public boolean isAvailable(ChipColor playerColor){
        return (playerColor == color) || (chips == 0);
    }

    public void addChip(ChipColor playerColor){
        if (chips++ == 0){
            color = playerColor;
        }
    }

    public void takeChip(){
        if (--chips == 0){
            color = null;
        }
    }
}

