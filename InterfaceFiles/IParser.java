package InterfaceFiles;

import SourceFiles.*;
import java.util.ArrayList;

public interface IParser {

    public void requestToken(IToken t);

    public ArrayList<ILineStatement> getIntermediateRep(); //COMMENT: better naming: parse
}
