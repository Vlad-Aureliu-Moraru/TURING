package Logic.MachineLogic;

public class Band {
    private String toCheck;
    private int HEAD = 1;

    public void setToCheck(String toCheck) {
        this.toCheck = "B"+toCheck+"B";
    }
    public char getCharAtHead(){
        return toCheck.charAt(HEAD);
    }
    public void moveHeadRight(){
        if (HEAD+1 >= toCheck.length()) {
           System.out.println("Reached End of the tape");
        }else {
            HEAD++;
        }

    }
    public void moveHeadLeft(){
        if (HEAD-1 < 0) {
            System.out.println("Reached Start of the tape");
        }else {
            HEAD--;
        }
    }
    public void replaceAtHead(char c){
        toCheck = toCheck.substring(0, HEAD) + c + toCheck.substring(HEAD + 1);
    }
    public String getToCheck() {
        return toCheck;
    }

    //    private String currentState;
}
