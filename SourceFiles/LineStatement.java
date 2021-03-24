
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

    public void setLabel(ILabel label) {
        this.label = label;
    }

    public IInstruction getMnemonic() {
        return this.mnemonic;
    }

    public void setMnemonic(IInstruction mnemonic) {
        this.mnemonic = mnemonic;
    }

    public IComment getComment() {
        return this.comment;
    }

    public void setComment(IComment comment) {
        this.comment = comment;
    }

    public String toString() {
        return "[" + mnemonic + "]";
    }
}