//Shriman,Auvigoo, Shangirna

import java.util.ArrayList;

public class Parser implements IParser {

    private ArrayList<LineStatement> IR = new ArrayList<LineStatement>();
    private ArrayList<Token> tokens; // tokens received from the lexical analyzer

    // default constructor
    public Parser() {
        tokens = new ArrayList<Token>();
    }

    // This method will be used to take the inputs from the Lexical analyzer
    public void requestToken(Token t) {
        tokens.add(t);
    }

    // Returns the IR that the code generator will use
    public ArrayList<LineStatement> getIntermediateRep() {

        ArrayList<LineStatement> IR = new ArrayList<LineStatement>(); // Intermediate representation array list

        Label label = null; // label field
        Instruction mnemonic = null; // Instruction field
        Comment comment = null; // comment field

        // loop through the array of tokens to perform the parsing
        for (Token token : tokens) {

            // Check if the current token does not contain an end of line marker
            if (token.getEOL() == "") {

                // check if token is a mnemonic
                if (token.getComment() == null && token.getLabel() == null) {
                    mnemonic = token.getInstruction();
                }

                // check if token is a label
                if (token.getInstruction() == null && token.getComment() == null) {
                    label = token.getLabel();
                }

                // check if token is a comment
                if (token.getInstruction() == null && token.getLabel() == null) {
                    comment = token.getComment();
                }

            }

            // check if the current token contains an end of line marker
            if (token.getEOL() == "\n") {

                // check if token is a mnemonic
                if (token.getComment() == null && token.getLabel() == null) {
                    mnemonic = token.getInstruction();
                }

                // check if token is a label
                if (token.getInstruction() == null && token.getComment() == null) {
                    label = token.getLabel();
                }

                // check if token is a comment
                if (token.getInstruction() == null && token.getLabel() == null) {
                    comment = token.getComment();
                }

                // when all the fields are set, create a line statement and parse it in the
                // Intermediate representation
                LineStatement line = new LineStatement(label, mnemonic, comment, token.getEOL());
                IR.add(line);

                // reinitilize fields to loop again
                label = null;
                mnemonic = null;
                comment = null;

            }
        }
        return IR;

    }

}