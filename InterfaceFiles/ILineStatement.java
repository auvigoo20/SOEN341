package InterfaceFiles;

import SourceFiles.*;

public interface ILineStatement {

    public ILabel getLabel();

    public IInstruction getMnemonic();

    public IComment getComment();

}
