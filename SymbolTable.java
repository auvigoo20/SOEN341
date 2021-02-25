import java.util.LinkedHashMap;

public class SymbolTable implements ISymbolTable{

    //Using linked hash map to preserve order of tokens
    private LinkedHashMap<String, Token> symbolTable;

    public SymbolTable(){
        symbolTable = new LinkedHashMap<String, Token>();
    }

    //Only inserting mnemonic in symbol table for sprint 2
    public void insertMnemonic(String key, Token value){
        symbolTable.put(key, value.getInstruction());
    }

    public LinkedHashMap<String, Token> gHashMap(){
        return symbolTable;
    }


}