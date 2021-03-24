package InterfaceFiles;

import SourceFiles.*;

public interface ILineStatement {

    public ILabel getLabel();

    public void setLabel(ILabel label);

    public IInstruction getMnemonic();

    public void setMnemonic(IInstruction mnemonic);

    public IComment getComment();

    public void setComment(IComment comment);

}
