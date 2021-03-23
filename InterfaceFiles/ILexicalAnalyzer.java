package InterfaceFiles;

import SourceFiles.*;

public interface ILexicalAnalyzer { // Interface to be used for the lexicalAnalyzer

    public IToken scan(); //COMMENT: still dependency problem, constructor injection better, also parser not needed

    public IToken getToken(); //COMMENT: getToken, nextToken; better naming, should return the interface token


public boolean getFinishScanning();


public void setFinishScanning(boolean fs);
}
