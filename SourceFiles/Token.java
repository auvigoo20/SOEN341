
package SourceFiles;

import InterfaceFiles.*;
import java.util.ArrayList;

public class Token implements IToken {

    private Label label;
    private Instruction instruction;
    private Comment comment;
    private String EOL;

    private Position position;
    private boolean isValid;



    public Token() {
        position = new Position();
        label = null;
        instruction = null;
        comment = null;
        EOL = null;
        isValid = true;
    }

    // constructor used for the sake of Sprint 2
    public Token(Instruction mnemonic, String EOL) {
        // this.label = label;
        this.instruction = mnemonic;
        // this.comment = comment;
        this.EOL = EOL;
    }

    public Token(Label label, Instruction instruction, Comment comment, String EOL, Position position, boolean isValid){
    
    this.label = label;
    this.instruction = instruction;
    this.comment = comment;
    this.EOL = EOL;
    this.position = position;
    this.isValid = isValid;
    }

    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Instruction getInstruction() {
        return this.instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public Comment getComment() {
        return this.comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getEOL() {
        return this.EOL;
    }

    public void setEOL(String EOL) {
        this.EOL = EOL;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String toString() {
        return label + " " + instruction + " " + comment + " " + EOL;

    }

}
