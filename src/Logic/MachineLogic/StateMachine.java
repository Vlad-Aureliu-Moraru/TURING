package Logic.MachineLogic;

public class StateMachine {
    private String currentState;
    private String nextState;
    private String[] instruction = new String[2];
    private int index;

    public void setCurrentState(String currentState) {
        String regEx = "^q(0|[1-9]*)+$";
        if (currentState.matches(regEx)) {
            this.currentState = currentState;
        }else {
            throw new IllegalArgumentException("Invalid Current State" + currentState);
        }
    }
    public void setNextState(String nextState) {
        String regEx = "^q(0|[1-9]*)+$";
        if (nextState.matches(regEx)) {
            this.nextState = nextState;
        }else {
            throw new IllegalArgumentException("Invalid Next State");
        }
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
    public void setInstructions(String toReplace, String movement){
    String regExToReplace = "^./.+$";
    String regExMovement = "L|R|S|H";
    if (toReplace.matches(regExToReplace) && movement.matches(regExMovement)) {
        this.instruction[0] = toReplace;
        this.instruction[1] = movement;
    }else {
        throw new IllegalArgumentException("Invalid Instruction");
    }

    }
    public String getCurrentState() {
        return currentState;
    }
    public String getNextState() {
        return nextState;
    }
    public String[] getInstruction() {
        return instruction;
    }
    public String getInstructionMovement() {
        return instruction[1];
    }
    public String getInstructionToReplace() {
        return instruction[0];
    }
    public String getInstructionToReplace(int index) {
        return instruction[0].split("/")[index];
    }
    public String toString() {
        return index+"{" + currentState + ";" + instruction[0] + ":" + instruction[1]+ ";" + nextState+"}" ;
    }
}