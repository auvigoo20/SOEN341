package InterfaceFiles;

import SourceFiles.*;

public interface ILineStatement {

    public Label getLabel();

    public void setLabel(Label label);

    public Instruction getMnemonic();

    public void setMnemonic(Instruction mnemonic);

    public Comment getComment();

    public void setComment(Comment comment);

    public String getEol();

    public void setEol(String eol);

}
