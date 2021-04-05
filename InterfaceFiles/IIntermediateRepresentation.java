package InterfaceFiles;

import java.util.ArrayList;

import SourceFiles.*;

public interface IIntermediateRepresentation {

    public void addLineStatement(ILineStatement lineStatement);
    

    public ArrayList<ILineStatement> getIR();
    
}
