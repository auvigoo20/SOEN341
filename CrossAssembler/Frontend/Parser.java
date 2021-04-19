package CrossAssembler.Frontend;

import CrossAssembler.Core.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser implements IParser {

    private ArrayList<IToken> tokens; // tokens received from the lexical analyzer
    private IErrorReporter errorReporter;
    private IIntermediateRepresentation intermediateRepresentation;
    private ISymbolTable symbolTable;

    // Does not contain ALL 7 immediate instructions (br.i5, brf.15)
    private final String[] immediateMnemonics = { "enter.u5", "ldc.i3", "addv.u3", "ldv.u3", "stv.u3" };

    // Contains all inherent instructions
    private final String[] inherentMnemonics = { "halt", "pop", "dup", "exit", "ret", "not", "and", "or", "xor", "neg",
            "inc", "dec", "add", "sub", "mul", "div", "rem", "shl", "shr", "teq", "tne", "tlt", "tgt", "tle", "tge" };

    private final String[] relativeMnemonics = { "br.i8", "brf.i8", "ldc.i8", "ldv.u8", "stv.u8", "lda.i16" };

    public Parser(IErrorReporter errorReporter, ISymbolTable symbolTable) {
        this.tokens = new ArrayList<IToken>();
        this.errorReporter = errorReporter;
        this.intermediateRepresentation = new IntermediateRepresentation();
        this.symbolTable = symbolTable;
    }

    // This method will be used to take the inputs from the Lexical analyzer
    public void requestToken(IToken t) {
        tokens.add(t);
    }

    // Returns the IR that the code generator will use
    public IIntermediateRepresentation parse() {

        Label label = null; // label field
        Instruction instruction = null; // Instruction field
        Comment comment = null; // comment field

        for (int i = 0; i < tokens.size(); i++) {

            // --------IF TOKEN DOES NOT CONTAIN EOL----------------------------
            if (tokens.get(i).getEOL() == "") {

                if (tokens.get(i).getTokenString().equalsIgnoreCase(".cstring")) {

                    // check if the token that follows it is a string operand
                    // [.cstring "ABC]
                    // if the mnemonic has no operand, use Integer.MAX_VALUE as a placeholder of
                    // opcode in Mnemonic constructor
                    if (i != (tokens.size() - 1)) {
                        if (tokens.get(i + 1).getTokenString().contains("\"")) {

                            String cstringOpcode = getStringOpcode(tokens.get(i + 1).getTokenString());

                            instruction = new Instruction(new Mnemonic(tokens.get(i).getTokenString(), cstringOpcode),
                                    new Operand(tokens.get(i + 1).getTokenString()), tokens.get(i).getPosition());
                        }

                        else {
                            String error = "Error: A directive instruction must be followed by an array of characters in the operand field";
                            errorReporter.record(new ErrorMessage(error, new Position(
                                    tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                        }

                    }

                }

                // inherent mnemonics check:
                // if the token is found in the inherent mnemonic list
                if (Arrays.asList(inherentMnemonics).contains(tokens.get(i).getTokenString())) {

                    // Error handling: If inherent mnemonic has an operand, error
                    if (i != (tokens.size() - 1)) {

                        if (isNumber(tokens.get(i + 1).getTokenString())) {

                            String error = "Error: Instructions with inherent mode addressing do not have an operand field.";
                            errorReporter.record(new ErrorMessage(error, new Position(
                                    tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                        }

                        else {

                            int opcode = searchCode(tokens.get(i).getTokenString(), -1);

                            instruction = new Instruction(
                                    new Mnemonic(tokens.get(i).getTokenString(), opcode),
                                    tokens.get(i).getPosition());
                        }
                    }
                }

                // immediate mnemonic check:
                // if the token is found in the immediate mnemonic list
                else if (Arrays.asList(immediateMnemonics).contains(tokens.get(i).getTokenString())) {

                    if (i != (tokens.size() - 1)) {

                        // If the mnemonic is not followed by an operand
                        if (!isNumber(tokens.get(i + 1).getTokenString())) {
                            // error
                            String error = "Error: This immediate instruction must have a number as an operand field.";
                            errorReporter.record(new ErrorMessage(error, new Position(
                                    tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                        }

                        // If the mnemonic IS followed by an operand
                        else if (isNumber(tokens.get(i + 1).getTokenString())) {

                            // If the operand that follows the mnemonic does not fall within its allowed
                            // range
                            if (checkOperand(tokens.get(i), tokens.get(i + 1)) == false) {
                                String error = "";
                                if (tokens.get(i).getTokenString().contains(".u5")) {
                                    error = "Error: The immediate instruction " + tokens.get(i).getTokenString()
                                            + " must have a 5-bit unsigned operand number ranging from 0 to 31.";
                                    errorReporter.record(new ErrorMessage(error,
                                            new Position(tokens.get(i).getPosition().getColumn(),
                                                    tokens.get(i).getPosition().getLine())));

                                } else if (tokens.get(i).getTokenString().contains(".i3")) {
                                    error = "Error: The immediate instruction " + tokens.get(i).getTokenString()
                                            + " must have a 3-bit signed operand number ranging from -4 to 3.";
                                    errorReporter.record(new ErrorMessage(error,
                                            new Position(tokens.get(i).getPosition().getColumn(),
                                                    tokens.get(i).getPosition().getLine())));

                                } else if (tokens.get(i).getTokenString().contains(".u3")) {
                                    error = "Error: The immediate instruction " + tokens.get(i).getTokenString()
                                            + " must have a 3-bit unsigned operand number ranging from 0 to 7.";
                                    errorReporter.record(new ErrorMessage(error,
                                            new Position(tokens.get(i).getPosition().getColumn(),
                                                    tokens.get(i).getPosition().getLine())));

                                }
                            } else {
                                int operand = Integer.parseInt(tokens.get(i + 1).getTokenString());
                                int opcode = searchCode(tokens.get(i).getTokenString(), operand);

                                instruction = new Instruction(new Mnemonic(tokens.get(i).getTokenString(), opcode),
                                        new Operand(tokens.get(i + 1).getTokenString()), tokens.get(i).getPosition());
                            }
                        }
                    }
                    // The immediate mnemonic is the LAST token, meaning it does not have an operand
                    // after it
                    else {
                        String error = "Error: This immediate instruction must have a number as an operand field.";
                        errorReporter
                                .record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(),
                                        tokens.get(i).getPosition().getLine())));
                    }
                }
                // Relative mnemonic check
                else if (Arrays.asList(relativeMnemonics).contains(tokens.get(i).getTokenString())) {

                    if (i != (tokens.size() - 1)) {

                        // When token is a relative instruction that has a label as an operand
                        if (tokens.get(i).getTokenString().equals("br.i8")
                                || tokens.get(i).getTokenString().equals("brf.i8")) {
                            // if token is followed by a number then pop error
                            if (isNumber(tokens.get(i + 1).getTokenString())) {
                                String error = "Error: This relative instruction must have a label as an operand.";
                                errorReporter.record(
                                        new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(),
                                                tokens.get(i).getPosition().getLine())));
                            } else {
                                instruction = new Instruction(
                                        new Mnemonic(tokens.get(i).getTokenString(), Integer.MAX_VALUE),
                                        new Operand(tokens.get(i + 1).getTokenString()), tokens.get(i).getPosition());
                            }
                        }
                        // When token is relative instruction that has an integer as an operand
                        else {

                            if (isNumber(tokens.get(i + 1).getTokenString())) {
                                if (checkOperand(tokens.get(i), tokens.get(i + 1))) {
                                    int operand = Integer.parseInt(tokens.get(i + 1).getTokenString());
                                    int opcode = searchCode(tokens.get(i).getTokenString(), operand);

                                    instruction = new Instruction(new Mnemonic(tokens.get(i).getTokenString(), opcode),
                                            new Operand(tokens.get(i + 1).getTokenString()),
                                            tokens.get(i).getPosition());
                                } else {
                                    String error = "Error: The operand of this relative instruction is out of range.";
                                    errorReporter.record(new ErrorMessage(error,
                                            new Position(tokens.get(i).getPosition().getColumn(),
                                                    tokens.get(i).getPosition().getLine())));
                                }
                            } else {
                                instruction = new Instruction(
                                        new Mnemonic(tokens.get(i).getTokenString(), Integer.MAX_VALUE),
                                        new Operand(tokens.get(i + 1).getTokenString()), tokens.get(i).getPosition());
                            }
                        }
                    }
                }

                // if the token is a label
                else {
                    if (tokens.get(i).getPosition().getColumn() == 1) {
                        if (symbolTable.getSymbolTable().keySet().contains(tokens.get(i).getTokenString())) {
                            String error = "Error: This label has already been defined.";
                            errorReporter.record(new ErrorMessage(error, new Position(
                                    tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                        } else {
                            //ADDRESS TO CALCULATE
                            int address = 0;
                            label = new Label(tokens.get(i).getTokenString(), address, tokens.get(i).getPosition());
                            symbolTable.insertMnemonic(tokens.get(i).getTokenString(), label);
                        }
                    }
                }
                // *****LABEL CHECK FOR SPRINT 4*****
            }

            // Otherwise if the token contains the end of line marker "newLine"
            if (tokens.get(i).getEOL().equalsIgnoreCase("newLine")) {

                // check to see if .cstring mnemonic is valid. If not then print message
                if (tokens.get(i).getTokenString().equalsIgnoreCase(".cstring")) {

                    String error = "Error: A directive instruction must be followed by an array of characters in the operand field";
                    errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(),
                            tokens.get(i).getPosition().getLine())));
                }

                // Check to see if the token is an inherent mnemonic
                if (Arrays.asList(inherentMnemonics).contains(tokens.get(i).getTokenString())) {

                    // Error handling: If inherent mnemonic has an operand, send it to error
                    // reporter with error message and token details
                    if (i != (tokens.size() - 1)) {

                        if (isNumber(tokens.get(i + 1).getTokenString())) {
                            String error = "Error: Instructions with inherent mode addressing do not have an operand field.";
                            errorReporter.record(new ErrorMessage(error, new Position(
                                    tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                        }

                        else {
                            int operand = Integer.parseInt(tokens.get(i + 1).getTokenString());
                            int opcode = searchCode(tokens.get(i).getTokenString(), operand);

                            instruction = new Instruction(new Mnemonic(tokens.get(i).getTokenString(), opcode),
                                    new Operand(tokens.get(i + 1).getTokenString()), tokens.get(i).getPosition());
                        }
                    }
                }

                // Immediate Mnemonic Check: if the token is found in the immediate mnemonic
                // list
                else if (Arrays.asList(immediateMnemonics).contains(tokens.get(i).getTokenString())) {
                    String error = "Error: This immediate instruction must have a number as an operand field.";
                    errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(),
                            tokens.get(i).getPosition().getLine())));
                }
                // Comment Check: if the token contains a semi-colon (;)
                else if (tokens.get(i).getTokenString().contains(";")) {
                    comment = new Comment(tokens.get(i).getTokenString(), tokens.get(i).getPosition());
                }
                // check for relative
                else if (Arrays.asList(relativeMnemonics).contains(tokens.get(i).getTokenString())) {
                    String error = "Error: This relative instruction must have a label as an operand.";
                    errorReporter.record(new ErrorMessage(error, new Position(tokens.get(i).getPosition().getColumn(),
                            tokens.get(i).getPosition().getLine())));
                }
                // *****LABEL CHECK FOR SPRINT 4*****
                else {
                    if (tokens.get(i).getPosition().getColumn() == 1) {
                        if (symbolTable.getSymbolTable().keySet().contains(tokens.get(i).getTokenString())) {
                            String error = "Error: This label has already been defined.";
                            errorReporter.record(new ErrorMessage(error, new Position(
                                    tokens.get(i).getPosition().getColumn(), tokens.get(i).getPosition().getLine())));
                        } else {
                            //ADDRESS TO CALCULATE
                            int address = 0;

                            label = new Label(tokens.get(i).getTokenString(), address, tokens.get(i).getPosition());
                            symbolTable.insertMnemonic(tokens.get(i).getTokenString(), label);
                        }
                    }
                }

                // Line statement object initialization
                LineStatement line = new LineStatement(label, instruction, comment);
                // Add to the IR array list
                intermediateRepresentation.addLineStatement(line);

                // reinitilize fields to loop again
                label = null;
                instruction = null;
                comment = null;
            }
        }
        return intermediateRepresentation;
    }

    public void getVerbose(){
        String verbose = String.format("%-10s%-10s%-10s", "Name", "Type", "Addr/Code");
        verbose += "\n";

        for(String key : symbolTable.getSymbolTable().keySet()){
            String name = key;
            String classPath = symbolTable.getSymbolTable().get(key).getClass().toString();
            String type = classPath.substring(classPath.lastIndexOf(".") + 1);
            String code = String.format("%02X", symbolTable.getSymbolTable().get(key).getOpcodeOrAddress());
            verbose += String.format("%-10s%-10s%-10s", name, type, code);
            verbose += "\n";
        }

        System.out.println(verbose);
    }

    // "ldc.i8", "ldv.u8", "stv.u8", "lda.i16" -- operands
    private boolean checkOperand(IToken mnemonic, IToken operand) {

        if (mnemonic.getTokenString().contains(".u5")) {
            return Integer.parseInt(operand.getTokenString()) >= 0 && Integer.parseInt(operand.getTokenString()) <= 31;
        } else if (mnemonic.getTokenString().contains(".i3")) {
            return Integer.parseInt(operand.getTokenString()) >= -4 && Integer.parseInt(operand.getTokenString()) <= 3;
        } else if (mnemonic.getTokenString().contains(".u3")) {
            return Integer.parseInt(operand.getTokenString()) >= 0 && Integer.parseInt(operand.getTokenString()) <= 7;
        } else if (mnemonic.getTokenString().contains(".i8")) {
            return Integer.parseInt(operand.getTokenString()) >= -128
                    && Integer.parseInt(operand.getTokenString()) <= 127;
        } else if (mnemonic.getTokenString().contains(".u8")) {
            return Integer.parseInt(operand.getTokenString()) >= 0 && Integer.parseInt(operand.getTokenString()) <= 255;
        } else if (mnemonic.getTokenString().contains(".i16")) {
            return Integer.parseInt(operand.getTokenString()) >= -32768
                    && Integer.parseInt(operand.getTokenString()) <= 32767;
        }
        return true;
    }

    private boolean isNumber(String operand) {
        char[] chars = operand.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c) && c != '-') {
                return false;
            }
        }
        return true;
    }

    /**
     * Associate a hexadecimal code to the mnemonic
     * 
     * @param mnemonic the mnemonic to look the opcode for
     * @return opcode associated to the mnemonic
     */
    private int searchCode(String mnemonic, int operand) {

        // Inherent addressing
        if (mnemonic.equals("halt"))
            return 0;
        else if (mnemonic.equals("pop"))
            return 1;
        else if (mnemonic.equals("dup"))
            return 2;
        else if (mnemonic.equals("exit"))
            return 3;
        else if (mnemonic.equals("ret"))
            return 4;
        else if (mnemonic.equals("not"))
            return 12;
        else if (mnemonic.equals("and"))
            return 13;
        else if (mnemonic.equals("or"))
            return 14;
        else if (mnemonic.equals("xor"))
            return 15;
        else if (mnemonic.equals("neg"))
            return 16;
        else if (mnemonic.equals("inc"))
            return 17;
        else if (mnemonic.equals("dec"))
            return 18;
        else if (mnemonic.equals("add"))
            return 19;
        else if (mnemonic.equals("sub"))
            return 20;
        else if (mnemonic.equals("mul"))
            return 21;
        else if (mnemonic.equals("div"))
            return 22;
        else if (mnemonic.equals("rem"))
            return 23;
        else if (mnemonic.equals("shl"))
            return 24;
        else if (mnemonic.equals("shr"))
            return 25;
        else if (mnemonic.equals("teq"))
            return 26;
        else if (mnemonic.equals("tne"))
            return 27;
        else if (mnemonic.equals("tlt"))
            return 28;
        else if (mnemonic.equals("tgt"))
            return 29;
        else if (mnemonic.equals("tle"))
            return 30;
        else if (mnemonic.equals("tge"))
            return 31;

        // Immediate addressing
        else if (mnemonic.equals("enter.u5")) {

            int number = operand; // for next sprint, may change; operand connected to bits; assume error handler
                                  // takes care of non-integer operands
            int opcode = (number > 15) ? 0x70 : 0x80;
            opcode = opcode | number;

            return opcode;

        }

        else if (mnemonic.equals("ldc.i3")) {

            int number = operand; // for next sprint, may change; operand connected to bits; assume error handler
                                  // takes care of non-integer operands
            int opcode = 0x90;

            if (number >= 0)
                opcode = opcode | number;
            else
                opcode = opcode | (number & 0x07);

            return opcode;

        }

        else if (mnemonic.equals("addv.u3")) {

            int number = operand; // for next sprint, may change; operand connected to bits; assume error handler
                                  // takes care of non-integer operands
            int opcode = 0x98;
            opcode = opcode | number;

            return opcode;

        }

        else if (mnemonic.equals("ldv.u3")) {

            int number = operand; // for next sprint, may change; operand connected to bits; assume error handler
                                  // takes care of non-integer operands
            int opcode = 0xA0;
            opcode = opcode | number;

            return opcode;

        }

        else if (mnemonic.equals("stv.u3")) {

            int number = operand; // for next sprint, may change; operand connected to bits; assume error handler
                                  // takes care of non-integer operands
            int opcode = 0xA8;
            opcode = opcode | number;

            return opcode;
        }

        else
            return -1;
    }

    private String getStringOpcode(String operandString) {

        String cstring = "";

        cstring = operandString.substring(1, operandString.length()-1);

        char[] ch = cstring.toCharArray();

        StringBuilder builder = new StringBuilder();
        for (char c : ch) { // evaluate the hex for each char
            String Hex = String.format("%H", c);
            builder.append(Hex + " ");
        }
        return builder.toString() + "00";
    }

    // public static void main(String[] args) {

    //     SymbolTable st = new SymbolTable();

    //     ErrorReporter er = new ErrorReporter();
    //     LexicalAnalyzer la = new LexicalAnalyzer("rela02.asm", st, er);

    //     Parser parser = new Parser(er, st);

    //     while (la.getFinishScanning() == false) {
    //         IToken token = la.scan();
    //         if (token != null) {
    //             parser.requestToken(token);
    //         }
    //     }
    //     IIntermediateRepresentation IR = parser.parse();

    //     for (ILineStatement l : IR.getIR()) {
    //         if (l.getInstruction() != null) {
    //             System.out.print(l.getInstruction().getMnemonic().getMnemonicString() + " "
    //                     + l.getInstruction().getOperand().getOperandNumber() + " " + l.getInstruction().getMnemonic().getCStringOpcode());
    //         }
    //         if (l.getComment() != null) {
    //             System.out.print(" " + l.getComment().getCommentToken());
    //         }
    //         if (l.getLabel() != null) {
    //             System.out.println(" " + l.getLabel().getLabelToken());
    //         }
    //         System.out.println();
    //     }

    //     for (String s : st.getSymbolTable().keySet()) {
    //     System.out.println(s);
    //     System.out.println(String.format("%02X", st.getSymbolTable().get(s).getOpcodeOrAddress())  );
    //     }

    //     System.out.println(parser.getVerbose());

    //     // er.report();

    // }

}
