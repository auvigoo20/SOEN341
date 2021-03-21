import java.util.ArrayList;
import InterfaceFiles.*;
import java.util.ArrayList;
import InterfaceFiles.*;
import SourceFiles.*;

public class Driver {

    public static void main(String[] args) {

        // File name from the user input in the command line
        String fileName = "TestInherentMnemonics.asm";

        // Create a new parser
        Parser parser = new Parser();

        // Create a new symbol table
        SymbolTable symbolTable = new SymbolTable();

        // Create a new lexical analyzer with
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(fileName,symbolTable);

        // Create a new code generator
        CodeGenerator codeGenerator = new CodeGenerator();


        while(lexicalAnalyzer.getFinishScanning() == false){
            IToken token = lexicalAnalyzer.scan();
            if(token != null){
            parser.requestToken(token);
            }
        }

        // Store the intermediate representation in a variable
        ArrayList<ILineStatement> IR = parser.parse();

        // Traverse the intermediate representation using the symbol table
        codeGenerator.traverseIR(IR, symbolTable);

        // Generate a listing file
        codeGenerator.generateListing();

    }

}
