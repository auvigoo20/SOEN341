import java.util.ArrayList;
//parent class which contains Labe, Instruction and Comment classes
public class Token {
   
    private Label label;
    private Instruction instruction;
    private Comment comment;
    private String EOL;

//halt ;fsdfk


    public Token(){
        label = null;
        instruction=null;
        comment =null;
        EOL =null;
    }

    // constructor used for the sake of Sprint 2
    public Token(Instruction mnemonic, String EOL){
        // this.label = label;
        this.instruction = mnemonic;
        // this.comment = comment;
        this.EOL = EOL;
    }

    // public Token(Label label, Instruction instruction, Comment comment, String EOL){
    //     
    //     this.label = label
    //     this.instruction = instruction;
    //     this.comment - comment;
    //     this.EOL = EOL;
    // }

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


    public String toString(){
        return label + " " + instruction + " " + comment + " " + EOL;
        
    }


    
}
