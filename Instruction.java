public class Instruction extends Token {
    
    private String mnemonic;
    private int operand1;
    private int operand2;

    public Instruction(){ }

    public Instruction(String mnemonic){
        this.mnemonic = mnemonic;
    }

    public Instruction(String mnemonic, int operand1, int operand2){
        this.mnemonic = mnemonic;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }


    public String getMnemonic(){
    return mnemonic;
    }

    public void setMnemonic(String m){
        mnemonic =m;
    }
    
    public int getOperand1(){
        return operand1;
    }

    public int getOperand2(){
        return operand2;
    }

    public String toString(){
        return mnemonic;
    }

}

        
    