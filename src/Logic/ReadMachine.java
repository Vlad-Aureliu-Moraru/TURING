package Logic;

import Logic.MachineLogic.Machine;
import Logic.MachineLogic.StateMachine;

import javax.crypto.Mac;
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadMachine {
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private Machine machine;
    private int state =0;
    public ReadMachine(Machine machine) {
        this.machine = machine;
        try {
            fileReader = new FileReader("assets/MachineFile");
            bufferedReader = new BufferedReader(fileReader);
            System.out.println("File opened");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("#ALPHABET")){
                    state=0;
                    continue;
                }
                else if (line.equals("#MACHINE")){
                    state=1;
                    continue;
                }else if (line.equals("#INITIAL_STATE")){
                    state=2;
                    continue;
                }else if (line.equals("#FINAL_STATE")){
                    state=3;
                    continue;
                }
                if (state==0){
                    readInAlphabet(line);
                }
                else if (state==1){
                    readIntoStateMachine(line);
                }else if (state==2){
                    readStartState(line);
                }else if (state==3){
                    readFinalState(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(machine);
    }
    public void readIntoStateMachine(String line){
        if (line.isEmpty()){
            return;
        }
        line = line.replaceAll(" ","");
        StateMachine stateMachine = new StateMachine();
        line = line.trim();
        String[] split = line.split(";");
        stateMachine.setCurrentState(split[0]);
        String[] instructions = split[1].split(":");
        stateMachine.setInstructions(instructions[0],instructions[1]);
        stateMachine.setNextState(split[2]);
        machine.addState(stateMachine);
    }
    public void readStartState(String line){
        if (line.isEmpty()){
            return;
        }
        line = line.replaceAll(" ","");
        line = line.trim();
       machine.setStartState(line);
    }
    public void readFinalState(String line){
        if (line.isEmpty()){
            return;
        }
        line = line.replaceAll(" ","");
        line.trim();
        machine.addFinalState(line);
    }
    public void readInAlphabet(String line){
        if (line.isEmpty()){
            return;
        }
        line = line.replaceAll(" ","");
        line.trim();
        machine.addAlphabet(line);
    }
}
