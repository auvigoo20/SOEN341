package Tests;

import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;
public class TestLabelError {

    public static void main(String[] args){
        IToken token1 = new Token("Main", "", new Position(1,1));
        IToken token2 = new Token("Main", "", new Position(1,2));

        IErrorReporter errorReporter = new ErrorReporter();
        ISymbolTable symbolTable = new SymbolTable();

        IParser parser = new Parser(errorReporter, symbolTable);

        parser.requestToken(token1);
        parser.requestToken(token2);

        parser.parse();

        System.out.println("Test Label Error");

        //Expected output
        System.out.println("Error: This label has already been defined. @column: 1 @line: 2");

        //Actual output
        errorReporter.report();




    }
}
