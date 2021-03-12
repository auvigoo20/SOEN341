
import SourceFiles.*;
import InterfaceFiles.*;

public class TestInstruction {
    public static void main(String[] args) {

        Instruction i1 = new Instruction();
        i1.setMnemonic("halt");
        String result = i1.getMnemonic();

        System.out.println("Test Instruction");

        // Expected output
        System.out.println("halt");

        // Actual output
        System.out.println(result);

    }
}
