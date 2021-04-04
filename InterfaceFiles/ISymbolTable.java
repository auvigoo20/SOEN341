package InterfaceFiles;

import SourceFiles.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public interface ISymbolTable {

    public void insertMnemonic(String key, IMnemonic value);

    public IMnemonic getMnemonicObject(String key);

    public LinkedHashMap<String, IMnemonic> getSymbolTable();
}
