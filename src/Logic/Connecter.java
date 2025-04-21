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
    public Connecter() {
        readMachine = new ReadMachine(machine);
        currentState = machine.getStartState();
        band.setToCheck("0011");
//        band.replaceAtHead('X');
//        System.out.println(band.getToCheck());
        Touring();
    }
    public void Touring(){
        while (!halted){
        System.out.println("-STRING - " + band.getToCheck());
        String currentSymbol = String.valueOf(band.getCharAtHead());
        System.out.println("-Current State : "+currentState);
        StateMachine stateMachine =  machine.searchForTransition(currentState,currentSymbol);
        if (stateMachine==null){
            halted = true;
            throw new RuntimeException("Halted state not found" );
        }
        System.out.println("--found transition : "+stateMachine);
        String symboleReplacement = stateMachine.getInstructionToReplace()[1];
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

        System.out.println(band.getToCheck());
    }
}
