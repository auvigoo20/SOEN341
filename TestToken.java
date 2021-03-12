import SourceFiles.*;
import InterfaceFiles.*;

public class TestToken {
    public static void main(String[] args) {

        Instruction i1 = new Instruction("halt");
        String eol = "";

        Token t1 = new Token(i1, eol);

        System.out.println("Test Token");

        // Expected Output
        System.out.println("halt");

        // Actual output
        System.out.println(t1.getInstruction());
    }

}
