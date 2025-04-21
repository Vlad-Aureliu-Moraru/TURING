package Logic.MachineLogic;

import java.util.ArrayList;

public class Machine {
    private ArrayList<StateMachine> states = new ArrayList<>();
    private ArrayList<String> alphabet = new ArrayList<>();
    private String StartState;
    private ArrayList<String> finalStates = new ArrayList<>();
    public ArrayList<StateMachine> getStates() {
        return states;
    }
    public void addState(StateMachine state){
        states.add(state);
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }
    public void addAlphabet(String alphabet){
        String regEx = "^.+$";
        if(alphabet.matches(regEx)){
            this.alphabet.add(alphabet);
        }else {
            throw new IllegalArgumentException("Invalid Alphabet "+alphabet);
        }
    }
    public String getStartState() {
        return StartState;
    }
    public void setStartState(String startState) {
        String regEx = "^q0$";
        if(startState.matches(regEx)){
            StartState = startState;
        }else {
            throw new IllegalArgumentException("Invalid Start State "+startState);
        }
    }
    public ArrayList<String> getFinalStates() {
        return finalStates;
    }
    public void addFinalState(String finalState){
        String regEx = "^q(0|[1-9]*)$";
        if(finalState.matches(regEx)){
            this.finalStates.add(finalState);
        }else {
            throw new IllegalArgumentException("Invalid Final State "+finalState);
        }
    }
    public StateMachine searchForTransition(String currentState, String toReplace){
        for(StateMachine state : states){
            if(state.getCurrentState().equals(currentState) && state.getInstructionToReplace()[0].equals(toReplace)){
                return state;
            }
        }
        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Alphabet : "+alphabet+"\n");
        sb.append("Start State : "+StartState+"\n");
        sb.append("Final States : "+finalStates+"\n");
        for(StateMachine state : states){
            sb.append(state.toString()+"\n");
        }
        return sb.toString();
    }
}
