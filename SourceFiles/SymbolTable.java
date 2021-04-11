package SourceFiles;

import java.util.LinkedHashMap;
import InterfaceFiles.*;

public class SymbolTable implements ISymbolTable {

    //the key represents the mnemonic string, and the value is the Mnemonic object itself (contains mnemonic string and opcode)
    private LinkedHashMap<String, IMnemonic> symbolTable;

    public SymbolTable() {
        symbolTable = new LinkedHashMap<String, IMnemonic>();
    }

    public void insertMnemonic(String key, IMnemonic value) {
        symbolTable.put(key, value);
    }

    public IMnemonic getMnemonicObject(String key){
        return symbolTable.get(key);
    }

    public LinkedHashMap<String, IMnemonic> getSymbolTable() {
        return this.symbolTable;
    }


}