package InterfaceFiles;

import SourceFiles.*;

public interface ILexicalAnalyzer { // Interface to be used for the lexicalAnalyzer

    public void readFileByLine(IParser p, ISymbolTable symbolTable);

    public Token generateToken();

}
