package Tests;

import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;
import java.util.LinkedHashMap;

public class TestSymbolTable {

    public static void main(String[] args) {

        // Create new symbol table
        ISymbolTable symbolTable = new SymbolTable();

        // Create tokens to insert into symbol table
        IMnemonic mnemonic1 = new Mnemonic("enter.u5", 80);
        IMnemonic mnemonic2 = new Mnemonic("ldc.i3", 90);


        // Insert tokens into symbol table
        symbolTable.insertMnemonic("enter.u5", mnemonic1);
        symbolTable.insertMnemonic("ldc.i3", mnemonic2);

        LinkedHashMap<String, IToken> hashMap = symbolTable.getSymbolTable();

        // Expected Output
        System.out.println("Test Symbol Table");
        System.out.println("enter.u5 ldc.i3 ");

        // Actual Output
        for (String mnemonic : hashMap.keySet()) {

            System.out.print(mnemonic + " ");

        }
        System.out.println();

    }

}
