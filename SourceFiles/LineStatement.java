
package SourceFiles;

import InterfaceFiles.*;

public class LineStatement implements ILineStatement {

    // Attributes
    private ILabel label;
    private IInstruction mnemonic;
    private IComment comment;

    // Default constructor
    public LineStatement() {
    }

    // Regular constructor
    public LineStatement(ILabel label, IInstruction mnemonic, IComment comment) {
        this.label = label;
        this.mnemonic = mnemonic;
        this.comment = comment;

    }

    public ILabel getLabel() {
        return this.label;
    }

    public IInstruction getMnemonic() {
        return this.mnemonic;
    }

    public IComment getComment() {
        return this.comment;
    }
}