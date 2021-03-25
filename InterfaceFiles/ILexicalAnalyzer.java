package InterfaceFiles;

import SourceFiles.*;

public interface ILexicalAnalyzer { // Interface to be used for the lexicalAnalyzer

    
    
    public IToken scan();

    public IToken getToken();

    public boolean getFinishScanning();

    public void setFinishScanning(boolean fs);

    public ISymbolTable getSymbolTable();

    public void setSymbolTable(ISymbolTable symbolTable);

    
}
