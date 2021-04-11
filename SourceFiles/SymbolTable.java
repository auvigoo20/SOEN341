package SourceFiles;

import java.util.LinkedHashMap;
import InterfaceFiles.*;

public class SymbolTable implements ISymbolTable {

    // the key represents the mnemonic string, and the value is the Mnemonic object
    // itself (contains mnemonic string and opcode)
    private LinkedHashMap<String, IToken> symbolTable;

    public SymbolTable() {
        symbolTable = new LinkedHashMap<String, IToken>();
    }

    public void insertMnemonic(String key, IToken value) {
        symbolTable.put(key, value);
    }

    public IToken getMnemonicObject(String key) {
        return symbolTable.get(key);
    }

    public LinkedHashMap<String, IToken> getSymbolTable() {
        return this.symbolTable;
    }

}