package InterfaceFiles;

import SourceFiles.*;
import java.util.ArrayList;

public interface IParser {

    public void requestToken(IToken t); // used to request a token to the parser from the lexical analyzer

    public ArrayList<IToken> getTokenList(); //  returns the list of tokens received form the lexicaul analyzer

    public ArrayList<ILineStatement> parse(); // COMMENT: better naming: parse

    public boolean checkOperand(IToken mnemonic, IToken operand);
}
