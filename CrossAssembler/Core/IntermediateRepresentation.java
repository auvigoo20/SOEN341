package CrossAssembler.Core;

import java.util.ArrayList;

public class IntermediateRepresentation implements IIntermediateRepresentation {

    private ArrayList<ILineStatement> IR;

    public IntermediateRepresentation() {
        this.IR = new ArrayList<ILineStatement>();
    }

    public void addLineStatement(ILineStatement lineStatement) {
        IR.add(lineStatement);
    }

    public ArrayList<ILineStatement> getIR() {
        return this.IR;
    }
}
