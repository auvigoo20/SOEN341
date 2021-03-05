
import SourceFiles.*;
import InterfaceFiles.*;

import java.util.LinkedHashMap;

public class TestSymbolTable {

    public static void main(String[] args) {

        // Create new symbol table
        SymbolTable symbolTable = new SymbolTable();

        // Create tokens to insert into symbol table
        Token token1 = new Token(new Instruction("not"), "\n");
        Token token2 = new Token(new Instruction("and"), "\n");
        Token token3 = new Token(new Instruction("or"), "\n");

        // Insert tokens into symbol table
        symbolTable.insertMnemonic("not", token1);
        symbolTable.insertMnemonic("and", token2);
        symbolTable.insertMnemonic("or", token3);

        LinkedHashMap<String, Token> hashMap = symbolTable.gHashMap();

        // Expected Output
        System.out.println("Test Symbol Table");
        System.out.println("not:not and:and or:or ");

        // Actual Output
        for (String mnemonic : hashMap.keySet()) {

            System.out.print(mnemonic + ":");
            System.out.print(symbolTable.gHashMap().get(mnemonic));
            System.out.print(" ");

        }
        System.out.println();

    }

}
