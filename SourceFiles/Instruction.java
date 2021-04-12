package SourceFiles;

import InterfaceFiles.*;

public class Instruction extends Token implements IInstruction {

//NOTES FROM PROF: should have class Inherent and Relative

    // Attributes
    private IMnemonic mnemonic;
    private IOperand operand;
    private IPosition position;

    // default constructor
    public Instruction() {

    }
    //NOTES FROM PROF: Should also have class Operand that can be either int or Label
    // Parametrized constructor without operand field
    public Instruction(IMnemonic mnemonic, IPosition position) { //NOTES FROM PROF: should inject the object mnemonic class (should create mnemonic class: has all characteristics; size, type, opcode, operand, etc.)
        this.mnemonic = mnemonic;
        this.position = position;
    }

    // Parametrized constructor with operand field
    public Instruction(IMnemonic mnemonic, IOperand operand, IPosition position) {
        this.mnemonic = mnemonic;
        this.operand = operand;
        this.position = position;
    }


    public IMnemonic getMnemonic() {
        return mnemonic;
    }


    public IOperand getOperand() {
        return operand;
    }

    public IPosition getPosition() {
        return this.position;
    }

}
