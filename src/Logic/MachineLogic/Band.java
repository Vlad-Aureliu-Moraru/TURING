package Logic.MachineLogic;

public class Band {
    private String toCheck;
    private int HEAD = 1;
    public char[] toCheckArray;

    public void setToCheck(String toCheck) {
        this.toCheck = "B"+toCheck+"B";
        toCheckArray = new char[this.toCheck.length()];
        for (int i = 0; i < this.toCheck.length(); i++) {
            toCheckArray[i] = this.toCheck.charAt(i);
        }
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
    public char[] getToCheckArray() {
        return toCheckArray;
    }
    public void resetHead(){
        HEAD = 1;
    }
    public int getHead() {
        return HEAD;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tape : "+toCheck+"\n");
        for (int i = 0; i < toCheckArray.length; i++) {
            sb.append(toCheckArray[i]+" ; ");
        }
        return sb.toString();
    }
}
