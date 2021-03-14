package InterfaceFiles;

import SourceFiles.*;

public interface ILexicalAnalyzer { // Interface to be used for the lexicalAnalyzer

    public void readFileByLine(IParser p); //COMMENT: still dependency problem, constructor injection better, also parser not needed

    public Token generateToken(); //COMMENT: getToken, nextToken; better naming, should return the interface token

}
