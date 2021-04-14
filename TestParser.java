import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;

import java.util.ArrayList;

public class TestParser {

    public static void main(String[] args) {

        // Simulate 3 tokens being read by the lexical analyzer
        IToken t1 = new Token("enter.u5", "", new Position(1, 1));
        IToken t2 = new Token("10", "", new Position(2, 1));
        IToken t3 = new Token(";hello","newLine",new Position(3,1));

        IErrorReporter er = new ErrorReporter();
        IParser parser = new Parser(er);

        // Simulate the lexical analyzer sending 3 tokens to the parser
        parser.requestToken(t1);
        parser.requestToken(t2);
        parser.requestToken(t3);

        // Parser creating line statements from the tokens and inserting them into
        // the intermediate representation
        ArrayList<ILineStatement> IR = parser.parse();

        ILineStatement l0 = IR.get(0);


        // Expected Output
        System.out.println("Test Parser");
        System.out.println("enter.u5 10 ;hello");

        // Actual output
        System.out.print(l0.getMnemonic().getMnemonic() + " " + l0.getMnemonic().getOperand() + " "+ l0.getComment().getCommentToken());
        System.out.println();

    }

}
