
import SourceFiles.*;
import InterfaceFiles.*;

public class TestLexicalAnalyzer {

    public static void main(String[] args) {

        SymbolTable st = new SymbolTable();
        ErrorReporter er = new ErrorReporter();

        LexicalAnalyzer la = new LexicalAnalyzer("TestForLexical.asm", st, er);

        System.out.println("Test LexicalAnalyzer");

        //Expected output
        System.out.println("enter.u5 0 ; OK, number <u5> [0..31]. enter.u5 1 ; OK, number <u5> [0..31]. enter.u5 2 ; OK, number <u5> [0..31]. ");

        //Actual output
        while (la.getFinishScanning() == false) {
            IToken token = la.scan();
            if (token != null) {

                System.out.print(token.getTokenString() + " ");
            }
        }
        System.out.println();


    }
}
