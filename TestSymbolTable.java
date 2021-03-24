
import SourceFiles.*;
import InterfaceFiles.*;

import java.util.LinkedHashMap;

public class TestSymbolTable {

    public static void main(String[] args) {

        // Create new symbol table
        ISymbolTable symbolTable = new SymbolTable();

        // Create tokens to insert into symbol table
        IToken token1 = new Token(".cstring", "", new Position(1, 2));
        IToken token2 = new Token("enter.u5","",new Position(2,2));
        IToken token3 = new Token("halt", "newLine", new Position(3,3));

        // Insert tokens into symbol table
        symbolTable.insertMnemonic(".cstring", token1);
        symbolTable.insertMnemonic("enter.u5", token2);
        symbolTable.insertMnemonic("halt", token3);

        LinkedHashMap<String, IToken> hashMap = symbolTable.gHashMap();

        // Expected Output
        System.out.println("Test Symbol Table");
        System.out.println(".cstring enter.u5 halt ");

        // Actual Output
        for (String mnemonic : hashMap.keySet()) {

            System.out.print(mnemonic + " ");

        }
        System.out.println();

    }

}
