package SourceFiles;

import InterfaceFiles.*;

public class Instruction extends Token {

    private String mnemonic;
    private String operand;

    public Instruction() {
    }

    public Instruction(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public Instruction(String mnemonic, String operand) {
        this.mnemonic = mnemonic;
        this.operand = operand;
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
