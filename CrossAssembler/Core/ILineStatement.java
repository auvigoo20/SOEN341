package CrossAssembler.Core;

public interface ILineStatement {

    public ILabel getLabel();

    public IInstruction getInstruction();

    public IComment getComment();

}
