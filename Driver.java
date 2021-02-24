import java.util.ArrayList;
public class Driver {

    public static void main(String[] args){

        //File name from the user input in the command line
        String fileName = args[0];

        //Create a new lexical analyzer with
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(fileName);

        //Create a new parser
        Parser parser = new Parser();

        //Create a new symbol table
        SymbolTable symbolTable = new SymbolTable();

        //Create a new code generator
        CodeGenerator codeGenerator = new CodeGenerator();

        //Analyze the .asm file, send tokens to the parser and fill up the symbol table
        lexicalAnalyzer.readFileByLine(parser, symbolTable);

        //Store the intermediate representation in a variable
        ArrayList<LineStatement> IR = parser.getIntermediateRep();

        //Traverse the intermediate representation using the symbol table
        codeGenerator.traverseIR(IR, symbolTable);

        //Generate a listing file
        codeGenerator.generateListing();




    }

}
