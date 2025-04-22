package Logic;

import Logic.MachineLogic.Band;
import Logic.MachineLogic.Machine;
import Logic.MachineLogic.StateMachine;

public class Connecter {
    private ReadMachine readMachine;
    private Machine machine = new Machine();
    private Band band = new Band();

    private String currentState ;
    private boolean halted = false;
    private boolean accepted = false;
    private boolean transitionNotFound = false;
    public Connecter() {
        band.setToCheck("");
//        Touring();
    }
    public void readMachine(){
        readMachine = new ReadMachine(machine);
        currentState = machine.getStartState();
    }
    public void Touring(){
        while (!halted){
        System.out.println("-STRING - " + band.getToCheck());
        String currentSymbol = String.valueOf(band.getCharAtHead());
        System.out.println("-Current State : "+currentState);
        StateMachine stateMachine =  machine.searchForTransition(currentState,currentSymbol);
        if (stateMachine==null){
            System.out.println("---No transition found HALTING");
            halted = true;
            transitionNotFound = true;
            break;
        }
        System.out.println("--found transition : "+stateMachine);
        String symboleReplacement = stateMachine.getInstructionToReplace(1);
        System.out.println("---Replacing - " +band.getCharAtHead()+" - with - "+symboleReplacement.toCharArray()[0]);
        band.replaceAtHead(symboleReplacement.charAt(0));
        String instruction = stateMachine.getInstructionMovement();
        if (instruction.equals("R")){
            System.out.println("---Moving Right");
            band.moveHeadRight();
        }else if (instruction.equals("L")){
            System.out.println("---Moving Left");
            band.moveHeadLeft();
        }else if (instruction.equals("H")){
            System.out.println("---Halting");
            halted = true;
        }
        currentState = stateMachine.getNextState();
        }
        if (halted && !transitionNotFound && (machine.getFinalStates().contains(currentState)||currentState.equals(machine.getStartState()))){
            accepted = true;
        }
        System.out.println("---Accepted : "+accepted);
        System.out.println(band.getToCheck());
        halted = false;
        accepted = false;
        band.resetHead();
        currentState= machine.getStartState();
    }
    public Machine getMachine() {
        return machine;
    }
    public Band getBand() {
        return band;
    }

    public void setBandToCheck(String toCheck) {
        this.band.setToCheck(toCheck);
    }
}
