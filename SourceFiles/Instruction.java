package SourceFiles;

import InterfaceFiles.*;

public class Instruction extends Token {

    private String mnemonic;
    private String operand;
    private String instructionType;



    public Instruction() {
    }

    public Instruction(String mnemonic) {
        this.mnemonic = mnemonic;
        setInstructionType(mnemonic);
    }

    public Instruction(String mnemonic, String operand) {
        this.mnemonic = mnemonic;
        this.operand = operand;
        setInstructionType(mnemonic);
    }

    public String getInstructionType(){
        return instructionType;
    }

    public void setInstructionType(String mnemonic){
        if(mnemonic.equals(".cstring")){
            this.instructionType = "Directive";
            return;
        }
        
        if(mnemonic.contains(".")){
            this.instructionType = "Immediate";
        }
        else 
            this.instructionType = "Inherent";
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String m) {
        mnemonic = m;
    }

    public String getOperand() {
        return operand;
    }

    public String toString() {
        return mnemonic;
    }

}
