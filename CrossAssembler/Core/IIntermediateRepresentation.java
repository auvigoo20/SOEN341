package CrossAssembler.Core;
import java.util.ArrayList;

public interface IIntermediateRepresentation {

    public void addLineStatement(ILineStatement lineStatement);
    

    public ArrayList<ILineStatement> getIR();
    
}
