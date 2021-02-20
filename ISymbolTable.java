
import java.util.HashMap;
public interface ISymbolTable {
    
    public void insertMnemonic(String key, Token value);

    public HashMap<String, Token> gHashMap();
}
