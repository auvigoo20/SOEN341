package InterfaceFiles;

import SourceFiles.*;

public interface ILexicalAnalyzer { // Interface to be used for the lexicalAnalyzer

    
    
    public IToken scan();

    public IToken getToken();

    public boolean getFinishScanning();



    
}
