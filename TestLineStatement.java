import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;

public class TestLineStatement {
    public static void main(String args[]) {

        IPosition pos = new Position(1,2);
        IComment c1 = new Comment(";hello", pos);
        IInstruction i1 = new Instruction("halt",pos);
        ILineStatement l1 = new LineStatement(null, i1, c1);

        System.out.println("Test Line Statement");

        // Expected Output
        System.out.println("halt ;hello");

        // Actual output
        System.out.println(l1.getMnemonic().getMnemonic() + " " + l1.getComment().getCommentToken());
    }
}
