package Logic;

import Logic.MachineLogic.Machine;
import Logic.MachineLogic.StateMachine;

public class Connecter {
    private ReadMachine readMachine;
    private Machine machine = new Machine();

    public Connecter() {
        readMachine = new ReadMachine(machine);
    }
}
