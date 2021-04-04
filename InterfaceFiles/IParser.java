package InterfaceFiles;

import SourceFiles.*;
import java.util.ArrayList;

public interface IParser {

    public void requestToken(IToken t); // used to request a token to the parser from the lexical analyzer

    public ArrayList<IToken> getTokenList(); //  returns the list of tokens received form the lexical analyzer

    public ArrayList<ILineStatement> parse();


}
