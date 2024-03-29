package CrossAssembler.Frontend;

import CrossAssembler.Core.*;

public interface IParser {

    public void requestToken(IToken t); // used to request a token to the parser from the lexical analyzer

    public IIntermediateRepresentation parse();

    public void getVerbose();

}
