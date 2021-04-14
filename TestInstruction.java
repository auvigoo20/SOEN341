
import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;

public class TestInstruction {
    public static void main(String[] args) {

        String mnemonic = "enter.u5";
        String operand ="0";
        IPosition position = new Position(1,2);
        IInstruction i1 = new Instruction(mnemonic,operand,position);
        

        System.out.println("Test Instruction");

        // Expected output
        System.out.println("enter.u5 0 1 2");

        // Actual output
        System.out.println(i1.getMnemonic() + " " + i1.getOperand() + " " + i1.getPosition().getColumn() + " " + i1.getPosition().getLine());

    }
}
