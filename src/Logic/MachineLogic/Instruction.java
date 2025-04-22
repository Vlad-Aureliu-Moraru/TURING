package Logic.MachineLogic;

public class Instruction {
    private String tape;
    private int foundTransitionIndex;
    private int headPosition;

    public String getTape() {
        return tape;
    }
    public int getFoundTransitionIndex() {
        return foundTransitionIndex;
    }
    public int getHeadPosition() {
        return headPosition;
    }
    public void setTape(String tape) {
        this.tape = tape;
    }
    public void setFoundTransitionIndex(int foundTransitionIndex) {
        this.foundTransitionIndex = foundTransitionIndex;
    }
    public void setHeadPosition(int headPosition) {
        this.headPosition = headPosition;
    }
    public String toString() {
        return "{" + tape + ";" + foundTransitionIndex + ";" + headPosition+"}" ;
    }
}
