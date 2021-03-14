
import SourceFiles.*;
import InterfaceFiles.*;
import java.util.ArrayList;

public class TestLexicalAnalyzer {

    public static void main(String[] args) {

        // Create new lexical analyzer to read the .asm file
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

        // create new parser and symbol table objects
        Parser parser = new Parser();
        SymbolTable symbolTable = new SymbolTable();

        // insert data into parser and symbol table during lexical analysis
        lexicalAnalyzer.readFileByLine(parser, symbolTable);

        // get the values stored in the IR
        ArrayList<ILineStatement> IR = parser.parse();

        // Expected Output
        System.out.println("Test Lexical Analyzer");
        System.out.println(
                "[halt][pop][dup][exit][ret][not][and][or][xor][neg][inc][dec][add][sub][mul][div][rem][shl][shr][teq][tne][tlt][tgt][tle][tge][halt]");

        // Actual output
        for (ILineStatement l : IR) {
            System.out.print(l);
        }
        System.out.println();

    }
}
