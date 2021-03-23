
package SourceFiles;

import InterfaceFiles.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser implements IParser {

    private ArrayList<ILineStatement> IR;
    private ArrayList<IToken> tokens; // tokens received from the lexical analyzer
    private ErrorReporter errorReporter;

    // Does not contain ALL 7 immediate instructions (br.i5, brf.15)
    private final String[] immediateMnemonics = { "enter.u5", "ldc.i3", "addv.u3", "ldv.u3", "stv.u3" };

    // Contains all inherent instructions
    private final String[] inherentMnemonics = { "halt", "pop", "dup", "exit", "ret", "not", "and", "or", "xor", "neg",
            "inc", "dec", "add", "sub", "mul", "div", "rem", "shl", "shr", "teq", "tne", "tlt", "tgt", "tle", "tge" };

    // default constructor
    public Parser(ErrorReporter errorReporter) {
        this.tokens = new ArrayList<IToken>();
        this.errorReporter = errorReporter;
        this.IR = new ArrayList<ILineStatement>();
    }

    // This method will be used to take the inputs from the Lexical analyzer
    public void requestToken(IToken t) {
        tokens.add(t);
    }

    public ArrayList<IToken> getTokens() {
        return tokens;
    }

    // Returns the IR that the code generator will use
    public ArrayList<ILineStatement> parse() {

        Label label = null; // label field
        Instruction mnemonic = null; // Instruction field
        Comment comment = null; // comment field

        for (int i = 0; i < tokens.size(); i++) {

            // --------IF TOKEN DOES NOT CONTAIN EOL----------------------------
            if (tokens.get(i).getEOL() == "") {

                // inherent mnemonics check:
                // if the token is found in the inherent mnemonic list
                if (Arrays.asList(inherentMnemonics).contains(tokens.get(i).getTokenString())) {

                    // Error handling: If inherent mnemonic has an operand, error
                    if (i != (tokens.size() - 1)) {

                        if (tokens.get(i + 1).getTokenString().matches("[0-9-]*")) {

                            String error = "Error: Instructions with inherent mode addressing do not have an operand field.";
                            errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                        }

                        
                        else {
                            mnemonic = new Instruction(tokens.get(i).getTokenString(), tokens.get(i).getPosition());
                        }
                    }
                }

                // immediate mnemonic check:
                // if the token is found in the immediate mnemonic list
                else if (Arrays.asList(immediateMnemonics).contains(tokens.get(i).getTokenString())) {

                    if (i != (tokens.size() - 1)) {

                        // If the mnemonic is not followed by an operand
                        if (!tokens.get(i + 1).getTokenString().matches("[0-9-]*")) {
                            // error
                            String error = "Error: This immediate instruction must have a number as an operand field.";
                            errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                        }

                        // If the mnemonic IS followed by an operand
                        else if (tokens.get(i + 1).getTokenString().matches("[0-9-]*")) {

                            // If the operand that follows the mnemonic does not fall within its allowed
                            // range
                            if (checkOperand(tokens.get(i), tokens.get(i + 1)) == false) {
                                String error = "";
                                if (tokens.get(i).getTokenString().contains(".u5")) {
                                    error = "Error: The immediate instruction " + tokens.get(i).getTokenString()
                                            + " must have a 5-bit unsigned operand number ranging from 0 to 31.";
                                    errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));

                                } else if (tokens.get(i).getTokenString().contains(".i3")) {
                                    error = "Error: The immediate instruction " + tokens.get(i).getTokenString()
                                            + " must have a 3-bit signed operand number ranging from -4 to 3.";
                                    errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                                    
                                } else if (tokens.get(i).getTokenString().contains(".u3")) {
                                    error = "Error: The immediate instruction " + tokens.get(i).getTokenString()
                                            + " must have a 3-bit unsigned operand number ranging from 0 to 7.";
                                    errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));

                                }
                            } 
                            else {
                                mnemonic = new Instruction(tokens.get(i).getTokenString(),
                                        tokens.get(i + 1).getTokenString(), tokens.get(i).getPosition());
                            }
                        }
                    }
                    // The immediate mnemonic is the LAST token, meaning it does not have an operand
                    // after it
                    else {
                        String error = "Error: This immediate instruction must have a number as an operand field.\n@column: ";
                        errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                    }

                }

                // *****LABEL CHECK FOR SPRINT 4*****
            }

           // Otherwise if the token contains the end of line marker "newLine"
            else if (tokens.get(i).getEOL().equalsIgnoreCase("newLine")) {

                //Check to see if the token is an inherent mnemonic
                if (Arrays.asList(inherentMnemonics).contains(tokens.get(i).getTokenString())) {

                    // Error handling: If inherent mnemonic has an operand, send it to error reporter with error message and token details
                    if (i != (tokens.size() - 1)) {

                        if (tokens.get(i + 1).getTokenString().matches("[0-9-]*")) {
                            String error = "Error: Instructions with inherent mode addressing do not have an operand field.";
                            errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                        }

                        else {
                            mnemonic = new Instruction(tokens.get(i).getTokenString());
                        }
                    }
                }

                // Immediate Mnemonic Check: if the token is found in the immediate mnemonic list
                else if (Arrays.asList(immediateMnemonics).contains(tokens.get(i).getTokenString())) {
                    String error = "Error: This immediate instruction must have a number as an operand field.";
                    errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                }
                //Comment Check: if the token contains a semi-colon (;)
                if (tokens.get(i).getTokenString().contains(";")) {
                    comment = new Comment(tokens.get(i).getTokenString(), tokens.get(i).getPosition());
                }
                // *****LABEL CHECK FOR SPRINT 4*****

                //Line statement object initialization
                LineStatement line = new LineStatement(label, mnemonic, comment, tokens.get(i).getEOL());
                // Add to the IR array list
                IR.add(line);

                // reinitilize fields to loop again
                // label = null;
                mnemonic = null;
                comment = null;
            }

        }
        return IR;
    }

    public boolean checkOperand(IToken mnemonic, IToken operand) {

        if (mnemonic.getTokenString().contains(".u5")) {
            return Integer.parseInt(operand.getTokenString()) >= 0 && Integer.parseInt(operand.getTokenString()) <= 31;
        } else if (mnemonic.getTokenString().contains(".i3")) {
            return Integer.parseInt(operand.getTokenString()) >= -4 && Integer.parseInt(operand.getTokenString()) <= 3;
        } else if (mnemonic.getTokenString().contains(".u3")) {
            return Integer.parseInt(operand.getTokenString()) >= 0 && Integer.parseInt(operand.getTokenString()) <= 7;
        }
        return true;
    }


    public static void main(String[] args) {

        SymbolTable st = new SymbolTable();

        ErrorReporter er = new ErrorReporter();
        LexicalAnalyzer la = new LexicalAnalyzer("TestImmediate.asm", st,er);

        Parser parser = new Parser(er);

        while (la.getFinishScanning() == false) {
            IToken token = la.scan();
            if (token != null) {
                parser.requestToken(token);
            }
        }
        ArrayList<ILineStatement> lineStatements = parser.parse();

        for (ILineStatement l : lineStatements) {
            if (l.getMnemonic() != null) {
                System.out.print(l.getMnemonic().getMnemonic() + " " + l.getMnemonic().getOperand());
            }
            if (l.getComment() != null) {
                System.out.print(" " + l.getComment().getCommentToken());
            }
            System.out.println();
        }

        er.report();
    }

}


