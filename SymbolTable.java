import java.util.LinkedHashMap;

public class SymbolTable{

    private LinkedHashMap<String, Token> symbolTable;

    public SymbolTable(){
        symbolTable = new LinkedHashMap<String, Token>();
    }

    public void insertMnemonic(String key, Token value){
         
        symbolTable.put(key, value.getInstruction());
    }

    public LinkedHashMap<String, Token> gHashMap(){
        return symbolTable;
    }


}