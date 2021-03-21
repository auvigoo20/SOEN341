package InterfaceFiles;

import SourceFiles.*;
import java.util.HashMap;

public interface ISymbolTable {

    public void insertMnemonic(String key, IToken value);

    public HashMap<String, Token> gHashMap();
}
