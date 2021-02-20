import java.util.HashMap;

public class SymbolTable{

    private HashMap<String, Token> symbolTable;

    public SymbolTable(){
        symbolTable = new HashMap<String, Token>();
    }

    public void insertMnemonic(String key, Token value){
         
        symbolTable.put(key, value.getInstruction());
    }

    public HashMap<String, Token> gHashMap(){
        return symbolTable;
    }


}