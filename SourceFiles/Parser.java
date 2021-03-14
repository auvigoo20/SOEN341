
package SourceFiles;
import InterfaceFiles.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser implements IParser {

    private ArrayList<ILineStatement> IR = new ArrayList<ILineStatement>();
    private ArrayList<IToken> tokens; // tokens received from the lexical analyzer

    //Does not contain ALL 7 immediate instructions (br.i5, brf.15)
    private final String[] immediateMnemonics = {"enter.u5", "ldc.i3", "addv.u3", "ldv.u3", "stv.u3"};

    //Contains all inherent instructions
    private final String[] inherentMnemonics = {"halt", "pop", "dup", "exit","ret","not","and","or","xor","neg","inc","dec","add","sub","mul","div","rem","shl","shr","teq","tne","tlt","tgt","tle","tge"};

    // default constructor
    public Parser() {
        tokens = new ArrayList<IToken>();
    }

    // This method will be used to take the inputs from the Lexical analyzer
    public void requestToken(IToken t) {
        tokens.add(t);
    }

    // Returns the IR that the code generator will use
    public ArrayList<ILineStatement> parse() {

        ArrayList<ILineStatement> IR = new ArrayList<ILineStatement>(); // Intermediate representation array list

        Label label = null; // label field
        Instruction mnemonic = null; // Instruction field
        Comment comment = null; // comment field

        // loop through the array of tokens to perform the parsing
        for (IToken token : tokens) {

            // Check if the current token does not contain an end of line marker
            if (token.getEOL() == "") {


                // check if token is a mnemonic (INSTRUCTION)
                if (token.getComment() == null && token.getLabel() == null) {
                    mnemonic = token.getInstruction();
                    
                    //Check if mnemonic is Immediate Type

                    if(mnemonic.getInstructionType().equalsIgnoreCase("Immediate")){

                        //Error 1: Check if immediate instructions are part of the 7 possible instructions (Check spelling and stuff)
                        boolean immediateInstructionError =  Arrays.asList(immediateMnemonics).contains(mnemonic.getMnemonic());
                        if(immediateInstructionError == false){
                            System.out.println(" Immediate instruction Error!"); // send token to error reporter with details


                        }

                        //Error 2: Check if operands attached to immediate instructions respect their defined range of values

                        if(checkOperand(mnemonic) == false){
                            System.out.println("Operand Error!");

                        }
                    }

                    //Check if mnemonic is Inherent Type
                    //Error 3: Check if inherent instructions are part of the big list
                    if(mnemonic.getInstructionType().equalsIgnoreCase("Inherent")){
                        boolean inherentInstructionError = Arrays.asList(inherentMnemonics).contains(mnemonic.getMnemonic());
                        if(inherentInstructionError == false){
                            System.out.println(" Inherent Error!");
                        }
                    }
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

                    //Check if mnemonic is Immediate Type
                    if(mnemonic.getInstructionType().equalsIgnoreCase("Immediate")){
                        
                        //Error 1: Check if immediate instructions are part of the 7 possible instructions (Check spelling and stuff)
                        boolean immediateInstructionError =  Arrays.asList(immediateMnemonics).contains(mnemonic.getMnemonic());
                        if(immediateInstructionError == false){
                            System.out.println("Error!");
                        }

                      //Error 2: Check if operands attached to immediate instructions respect their defined range of values
                        if(checkOperand(mnemonic) == false){
                            System.out.println("Operand Error!");
                        }
                    }
                    
                    //Check if mnemonic is Inherent Type
                    //Error 3: Check if inherent instructions are part of the big list
                    if(mnemonic.getInstructionType().equalsIgnoreCase("Inherent")){
                        boolean inherentInstructionError = Arrays.asList(inherentMnemonics).contains(mnemonic.getMnemonic());
                        if(inherentInstructionError == false){
                            System.out.println(" Inherent Error!");
                        }
                    }
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

    public boolean checkOperand(Instruction m){

        if(m.getMnemonic().contains(".u5")){
            return m.getOperand() >= 0 && m.getOperand() <= 31;
        }
        else if(m.getMnemonic().contains(".i3")){
            return m.getOperand() >= -4 && m.getOperand() <= 3;
        }
        
        else if(m.getMnemonic().contains(".u3")){
            return m.getOperand() >= 0 && m.getOperand() <= 7;
        }

        return true;
    }

}




/**
 * Error 1: Check if immediate instructions are part of the 7 possible instructions (Check spelling and stuff)
 * Error 2: Check if operands attached to immediate instructions respect their defined range of values
 * Error 3: Check if inherent instructions are part of the big list
 * 
 * -----Lexical error
 * Error1:  if the token created matches a previously created token 
 * Error2: if there are no EOL markers
 */