package Frontend;

import Components.*;
import java.util.LinkedHashMap;

public interface ISymbolTable {

    public void insertMnemonic(String key, IToken value);

    public IToken getMnemonicObject(String key);

    public LinkedHashMap<String, IToken> getSymbolTable();
}
