package CrossAssembler.Core;

public class LineStatement implements ILineStatement {

    // Attributes
    private ILabel label;
    private IInstruction instruction;
    private IComment comment;

    // Default constructor
    public LineStatement() {
    }

    // Regular constructor
    public LineStatement(ILabel label, IInstruction instruction, IComment comment) {
        this.label = label;
        this.instruction = instruction;
        this.comment = comment;

    }

    public ILabel getLabel() {
        return this.label;
    }

    public IInstruction getInstruction() {
        return this.instruction;
    }

    public IComment getComment() {
        return this.comment;
    }
}