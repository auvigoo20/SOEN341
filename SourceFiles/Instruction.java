package SourceFiles;

import InterfaceFiles.*;

public class Instruction extends Token implements IInstruction {

    // Attributes
    private String mnemonic;
    private String operand;
    private IPosition position;
    private String instructionType;

    // default constructor
    public Instruction() {

    }

    // Parametrized constructor without operand field
    public Instruction(String mnemonic, IPosition position) {
        this.mnemonic = mnemonic;
        setInstructionType(mnemonic);
        this.position = position;
    }

    // Parametrized constructor with operand field
    public Instruction(String mnemonic, String operand, IPosition position) {
        this.mnemonic = mnemonic;
        this.operand = operand;
        this.position = position;
        setInstructionType(mnemonic);
    }

    // get the instruction type
    public String getInstructionType() {
        return instructionType;
    }

    // Set the instruction type according to the mnemonic
    public void setInstructionType(String mnemonic) {
        if (mnemonic.equals(".cstring")) {
            this.instructionType = "Directive";
            return;
        }

        if (mnemonic.contains(".")) {
            this.instructionType = "Immediate";
        } else
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

    public IPosition getPosition() {
        return this.position;
    }

}
