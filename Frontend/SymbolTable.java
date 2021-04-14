package Frontend;
import Components.*;

import java.util.LinkedHashMap;


public class SymbolTable implements ISymbolTable {

    private LinkedHashMap<String, IToken> symbolTable;

    public SymbolTable() {
        symbolTable = new LinkedHashMap<String, IToken>();
    }

    public void insertMnemonic(String key, IToken value) {
        symbolTable.put(key, value);
    }

    public IToken getMnemonicObject(String key){
        return symbolTable.get(key);
    }

    public LinkedHashMap<String, IToken> getSymbolTable() {
        return this.symbolTable;
    }


}