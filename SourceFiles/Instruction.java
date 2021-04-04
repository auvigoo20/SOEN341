package SourceFiles;

import InterfaceFiles.*;

public class Instruction extends Token implements IInstruction {

    // Attributes
    private String mnemonic;
    private IOperand operand;
    private IPosition position;
    private String instructionType;

    // default constructor
    public Instruction() {

    }
    //NOTES FROM PROF: Should also have class Operand that can be either int or Label
    // Parametrized constructor without operand field
    public Instruction(String mnemonic, IPosition position) { //NOTES FROM PROF: should inject the object mnemonic class (should create mnemonic class: has all characteristics; size, type, opcode, operand, etc.)
        this.mnemonic = mnemonic;
        setInstructionType(mnemonic);
        this.position = position;
    }

    // Parametrized constructor with operand field
    public Instruction(String mnemonic, IOperand operand, IPosition position) {
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
    private void setInstructionType(String mnemonic) {
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


    public IOperand getOperand() {
        return operand;
    }

    public IPosition getPosition() {
        return this.position;
    }

}
