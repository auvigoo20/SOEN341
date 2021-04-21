import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;

public class TestLineStatement {
    public static void main(String args[]) {

        IComment c1 = new Comment(";hello", new Position(1,2));

        IMnemonic mnemonic = new Mnemonic("halt", 0);

        IInstruction i1 = new Instruction(mnemonic, new Position(2, 2));
        ILineStatement l1 = new LineStatement(null, i1, c1);

        System.out.println("Test Line Statement");

        // Expected Output
        System.out.println("halt ;hello");

        // Actual output
        System.out.println(l1.getInstruction().getMnemonic().getMnemonicString() + " " + l1.getComment().getCommentToken());
    }
}
