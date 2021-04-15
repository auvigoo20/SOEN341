package CrossAssembler;

import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;
import CrossAssembler.Backend.*;

public class CrossAssembler implements ICrossAssembler {

    private IErrorReporter errorReporter;
    private ISymbolTable symbolTable;
    private ILexicalAnalyzer lexicalAnalyzer;
    private IParser parser;

    public CrossAssembler(String fileName) {
        this.errorReporter = new ErrorReporter();
        this.symbolTable = new SymbolTable();
        lexicalAnalyzer = new LexicalAnalyzer(fileName, this.symbolTable, this.errorReporter);
        parser = new Parser(this.errorReporter, this.symbolTable);
    }

    public void assemble(String options) {

        while (lexicalAnalyzer.getFinishScanning() == false) {
            IToken token = lexicalAnalyzer.scan();
            if (token != null) {
                parser.requestToken(token);
            }
        }

        if (errorReporter.getNumOfReports() > 0) {
            errorReporter.report();
            System.exit(0);
        }

        // Store the intermediate representation in a variable
        IIntermediateRepresentation IR = parser.parse();

        if (errorReporter.getNumOfReports() > 0) {
            errorReporter.report();
            System.exit(0);
        }

        // Create a new code generator
        CodeGenerator codeGenerator = new CodeGenerator(IR, symbolTable);

        // Generate a listing file
        if (options.equals("-l") || options.equals("-listing")) {
            codeGenerator.generateListing();
        }
    }

    // public static void main(String[] args) {
    // String fileName = null;

    // if (args.length == 0) {
    // fileName = "TestImmediate.asm";
    // }

    // // File name from the user input in the command line
    // else {
    // fileName = args[0];
    // }

    // CrossAssembler crossAssembler = new CrossAssembler(fileName);

    // crossAssembler.assemble(null);
    // }
}
