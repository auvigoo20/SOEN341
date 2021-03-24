package InterfaceFiles;

import SourceFiles.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public interface ISymbolTable {

    public void insertMnemonic(String key, IToken value);

    public LinkedHashMap<String, IToken> gHashMap();
}
