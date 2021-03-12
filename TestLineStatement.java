import SourceFiles.*;
import InterfaceFiles.*;

public class TestLineStatement {
    public static void main(String args[]) {

        Instruction i1 = new Instruction("halt");
        LineStatement l1 = new LineStatement();
        l1.setMnemonic(i1);

        System.out.println("Test Line Statement");

        // Expected Output
        System.out.println("halt");

        // Actual output
        System.out.println(l1.getMnemonic());
    }
}
