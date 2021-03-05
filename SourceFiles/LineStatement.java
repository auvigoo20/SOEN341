
package SourceFiles;

import InterfaceFiles.*;

public class LineStatement implements ILineStatement {

    private Label label;
    private Instruction mnemonic;
    private Comment comment;
    private String eol;

    // Default constructor
    public LineStatement() {
    }

    // Regular constructor
    public LineStatement(Label label, Instruction mnemonic, Comment comment, String eol) {
        this.label = label;
        this.mnemonic = mnemonic;
        this.comment = comment;
        this.eol = eol;

    }

    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Instruction getMnemonic() {
        return this.mnemonic;
    }

    public void setMnemonic(Instruction mnemonic) {
        this.mnemonic = mnemonic;
    }

    public Comment getComment() {
        return this.comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getEol() {
        return this.eol;
    }

    public void setEol(String eol) {
        this.eol = eol;
    }

    public String toString() {
        return "[" + mnemonic + "]";
    }
}