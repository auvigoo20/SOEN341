import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;

public class TestRelativeError {
    
    public static void main(String[] args){
        IToken token1 = new Token("br.i8", "", new Position(1,1));
        IToken token2 = new Token("5", "", new Position(1,1));

        IErrorReporter errorReporter = new ErrorReporter();
        ISymbolTable symbolTable = new SymbolTable();

        IParser parser = new Parser(errorReporter, symbolTable);

        parser.requestToken(token1);
        parser.requestToken(token2);

        parser.parse();

        System.out.println("Test Relative Error");

        //Expected output
        System.out.println("Error: This relative instruction must have a label as an operand. @column: 1 @line: 1");

        //Actual output
        errorReporter.report();




    }
}
