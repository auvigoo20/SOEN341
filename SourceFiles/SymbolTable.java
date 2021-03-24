package SourceFiles;

import java.util.LinkedHashMap;
import InterfaceFiles.*;

public class SymbolTable implements ISymbolTable {

    // Using linked hash map to preserve order of tokens
    private LinkedHashMap<String, IToken> symbolTable;

    public SymbolTable() {
        symbolTable = new LinkedHashMap<String, IToken>();
    }

    // Only inserting mnemonic in symbol table for sprint 2
    public void insertMnemonic(String key, IToken value) {
        symbolTable.put(key, value);
    }

    public LinkedHashMap<String, IToken> gHashMap() {
        return symbolTable;
    }

    public LinkedHashMap<String, IToken> getSymbolTable() {
        return this.symbolTable;
    }

    public void setSymbolTable(LinkedHashMap<String, IToken> symbolTable) {
        this.symbolTable = symbolTable;
    }

}