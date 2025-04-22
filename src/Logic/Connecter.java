package Logic;

import Logic.MachineLogic.Band;
import Logic.MachineLogic.Instruction;
import Logic.MachineLogic.Machine;
import Logic.MachineLogic.StateMachine;

import java.util.ArrayList;

public class Connecter {
    private ReadMachine readMachine;
    private Machine machine = new Machine();
    private Band band = new Band();

    private String currentState ;
    private boolean halted = false;
    private boolean accepted = false;
    private boolean transitionNotFound = false;

    private ArrayList<Instruction> instructions = new ArrayList<>();
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
            Instruction instructionToAdd = new Instruction();
        System.out.println("-STRING - " + band.getToCheck());
            instructionToAdd.setTape(band.getToCheck());
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
            instructionToAdd.setHeadPosition(band.getHead());
            instructionToAdd.setFoundTransitionIndex(stateMachine.getIndex());
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
        instructions.add(instructionToAdd);
        }
        if (halted && !transitionNotFound && (machine.getFinalStates().contains(currentState)||currentState.equals(machine.getStartState()))){
            accepted = true;
        }else{
            System.out.println("---Halting but not accepted because : "+halted+" "+transitionNotFound+" "+(machine.getFinalStates().contains(currentState)||currentState.equals(machine.getStartState())));
        }
        System.out.println("---Accepted : "+accepted);
        System.out.println(band.getToCheck());
    }
    public Machine getMachine() {
        return machine;
    }
    public Band getBand() {
        return band;
    }
    public boolean isAccepted() {
        return accepted;
    }
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }
    public void resetMachine(){
        instructions.clear();
        halted = false;
        transitionNotFound = false;
        accepted = false;
        band.resetHead();
        currentState= machine.getStartState();
    }
}
