package SourceFiles;

import InterfaceFiles.*;

public class Instruction extends Token {

    private String mnemonic;
    private String operand;
    private Position position;
    private String instructionType;

    public Instruction(){

    }

    public Instruction(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public Instruction(String mnemonic, Position position) {
        this.mnemonic = mnemonic;
        setInstructionType(mnemonic);
        this.position = position;
    }

    public Instruction(String mnemonic, String operand, Position position) {
        this.mnemonic = mnemonic;
        this.operand = operand;
        this.position = position;
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

    public Position getPosition(){
        return this.position;
    }

    public String toString() {
        return mnemonic;
    }

}
