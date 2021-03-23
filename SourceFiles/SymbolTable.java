package SourceFiles;

import java.util.LinkedHashMap;
import InterfaceFiles.*;

public class SymbolTable implements ISymbolTable {

    // Using linked hash map to preserve order of tokens
    private LinkedHashMap<String, Token> symbolTable;
    

    public SymbolTable() {
        symbolTable = new LinkedHashMap<String, Token>();
    }

    // Only inserting mnemonic in symbol table for sprint 2
    public void insertMnemonic(String key, IToken value) {
        symbolTable.put(key, value.getInstruction());
    }
    
    public LinkedHashMap<String, Token> gHashMap() {
        return symbolTable;
    }

    public LinkedHashMap<String, Token> getSymbolTable() {
		return this.symbolTable;
	}

	public void setSymbolTable(LinkedHashMap<String, Token> symbolTable) {
		this.symbolTable = symbolTable;
	}


}