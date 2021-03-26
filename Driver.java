import java.util.ArrayList;
import InterfaceFiles.*;
import SourceFiles.*;

public class Driver {


    //halt\n 50 ;comment\n
    //pop 
    public static void main(String[] args) {

        String fileName = null;

        if(args.length == 0){
            fileName = "TestImmediate.asm";
        }

        // File name from the user input in the command line
        else{
            fileName = args[0];
        }

        ErrorReporter errorReporter = new ErrorReporter();

        // Create a new symbol table
        SymbolTable symbolTable = new SymbolTable();

        // Create a new lexical analyzer with
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(fileName,symbolTable, errorReporter);

        // Create a new parser
        Parser parser = new Parser(errorReporter);

        while(lexicalAnalyzer.getFinishScanning() == false){
            IToken token = lexicalAnalyzer.scan();
            if(token != null){
            parser.requestToken(token);
            }
        }
        
        if(errorReporter.getErrorReports().size() > 0){
            errorReporter.report();
            System.exit(0);
        }

        // Create a new code generator
        CodeGenerator codeGenerator = new CodeGenerator();

        // Store the intermediate representation in a variable
        ArrayList<ILineStatement> IR = parser.parse();

        // Traverse the intermediate representation using the symbol table
        codeGenerator.traverseIR(IR, symbolTable);

        // Generate a listing file
        codeGenerator.generateListing();

    }

}
