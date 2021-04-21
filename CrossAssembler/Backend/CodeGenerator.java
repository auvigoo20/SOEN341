package CrossAssembler.Backend;
import CrossAssembler.Core.*;


import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList; 

import java.io.DataOutputStream; 


public class CodeGenerator implements ICGenerator {
    // Class attributes
    private ArrayList<String> opening = new ArrayList<>(); // array containing all openings of line statement: Line Addr
                                                           // Code
    private ArrayList<String> closing = new ArrayList<>(); // array containing all closings of line statement: Label Mne
                                                           // Operand Comment
    private String filename;

    private ArrayList<Integer> bytes = new ArrayList<>(); //array buffer containing each byte for the executable file

    // private String option; //to implement for next sprints

    private IIntermediateRepresentation IR;
    private ISymbolTable symbols;

    public CodeGenerator(String filename, IIntermediateRepresentation IR, ISymbolTable symbols) {
        this.filename = filename;
        this.IR = IR;
        this.symbols = symbols;
    }

    // methods
    /**
     * Traverse the IR using the symbol table
     * 
     * @param intRep  the IR produced
     * @param symbols the symbol table produced
     */
    private void traverseIR() {
        int lineNbr = 1; // line number decimal output, will be converted to hexadecimal
        int counter = 0; // decimal counter for line number and address, will be converted to hexadecimal
        String label = "";
        String mne = "";
        String operandString = null;
        int operandNumber = Integer.MAX_VALUE;
        String comment = "";
        String addr = "";
        String code = "";
        ArrayList<ILineStatement> intRep = IR.getIR();

        // for each line statement in IR
        for (int i = 0; i < intRep.size(); i++) {

            IPosition position = null;

            // Get the position from either the mnemonic or the comment (doesn't matter
            // which since only line number is needed)
            if (intRep.get(i).getInstruction() != null) {
                position = intRep.get(i).getInstruction().getPosition();
            }

            if (intRep.get(i).getComment() != null) {
                position = intRep.get(i).getComment().getPosition();
            }

            if (intRep.get(i).getLabel() != null) {
                position = intRep.get(i).getLabel().getPosition();
            }

            // Add empty lines (except for line number and address) if the .asm contains
            // empty lines
            if (position != null) {
                if (lineNbr < position.getLine()) {
                    for (int j = lineNbr; j < position.getLine(); j++) {
                        opening.add(String.format("%-5s%-5s%-6s", j, addr, ""));
                        closing.add(String.format("%-10s%-10s%-10s%-10s", "", "", "", ""));
                    }
                    lineNbr = position.getLine();
                }
            }

            // if the current line statement contains a mnemonic
            if (intRep.get(i).getInstruction() != null) {

                // for each symbol in the table
                for (String tableMne : symbols.getSymbolTable().keySet()) {

                    if (tableMne.equals(intRep.get(i).getInstruction().getMnemonic().getMnemonicString())) { // if table
                                                                                                             // mnemonic
                                                                                                             // is the
                                                                                                             // same as
                                                                                                             // line
                                                                                                             // mnemonic

                        // assign all the info into the appropriate variables
                        // label = intRep.get(i).getLabel().getLabelToken(); //for later
                        mne = intRep.get(i).getInstruction().getMnemonic().getMnemonicString();

                        // first check if operand is a number or a string
                        if (intRep.get(i).getInstruction().getOperand().isOperandNumber()) {
                            operandNumber = intRep.get(i).getInstruction().getOperand().getOperandNumber();
                        } else {
                            operandString = intRep.get(i).getInstruction().getOperand().getOperandString();
                        }

                        // find the opcode to the mnemonic
                        // .cstring "ABC"
                        if (mne.equals(".cstring")) { // evaluate opcode for cstring
                            String string = operandString;
                            String cstring;
                            if (string.length() > 4) // if there are more than 3 characters to evaluate (1 is a
                                                     // quotation mark)
                                cstring = string.substring(string.indexOf("\"") + 1, 4); // ignore 1st quotation mark
                            else
                                cstring = string.substring(string.indexOf("\"") + 1, string.lastIndexOf("\"")); // ignore
                                                                                                                // quotation
                                                                                                                // marks

                            char[] ch = cstring.toCharArray();

                            StringBuilder builder = new StringBuilder();
                            for (char c : ch) { // evaluate the hex for each char
                                String Hex = String.format("%H", c);
                                builder.append(Hex + " ");
                            }
                            code = builder.toString() + "00";
                        } else { // opcode for every other mnemonic
                            int opcode = intRep.get(i).getInstruction().getMnemonic().getOpcode();
                            if (opcode == -1)
                                code = "";
                            else
                                code = String.format("%02X", opcode); // Convert opcode to hexadecimal (2bit)
                        }
                    }
                }

            }

            // if the current line statement contains a comment
            if (intRep.get(i).getComment() != null) {
                comment = intRep.get(i).getComment().getCommentToken(); // to do this sprint
            }

            if(intRep.get(i).getLabel() != null){
                label = intRep.get(i).getLabel().getLabelToken();
            }

            addr = String.format("%04X", counter); // Convert decimal counter to hexadecimal (4bit)

            opening.add(String.format("%-5s%-5s%-6s", lineNbr, addr, code));

            if (operandString == null && operandNumber == Integer.MAX_VALUE) {
                closing.add(String.format("%-10s%-10s%-10s%-10s", label, mne, "", comment));
            } else if (operandString != null) {
                closing.add(String.format("%-10s%-10s%-10s%-10s", label, mne, operandString, comment));
            } else if (operandNumber != Integer.MAX_VALUE) {
                closing.add(String.format("%-10s%-10s%-10s%-10s", label, mne, operandNumber, comment));

            }

            // increment line number and address
            lineNbr++;
            counter++;
            label = "";
            mne = "";
            operandString = null;
            operandNumber = Integer.MAX_VALUE;
            comment = "";
        }
    }

    /**
     * Generates the listing file
     */
    public String generateListing() { // TO ASK: if this needs to be private. TODO: remove useless sysouts, file
                                      // naming

        String testStr;
        String listName = filename.substring(0, filename.length()-3) + "lst";
        traverseIR();

        // create listing file to output to
        PrintWriter outStr = null;
        try {
            outStr = new PrintWriter(new FileOutputStream(listName));

        } catch (FileNotFoundException e) {
            System.out.println("Cannot create file!");
        }

        // Create format header
        outStr.println(String.format("%-5s%-5s%-6s%-10s%-10s%-10s%-10s", "Line", "Addr", "Code", "Label", "Mne",
                "Operand", "Comments"));
        testStr = String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "Line", "Addr", "Code", "Label", "Mne", "Operand",
                "Comments"); // for testing purposes

        // Generating opening and closing line statement
        for (int i = 0; i < opening.size(); i++) {
            outStr.println(String.format("%-16s%-40s", opening.get(i), closing.get(i)));
            testStr = testStr + (String.format("[%-16s%-40s]", opening.get(i), closing.get(i))); // for testing purposes
        }

        // close listing file
        outStr.close();
        return testStr;
    }

    //Creating the executable file
    public String generateExe() {
        String exeName = filename.substring(0, filename.length()-3) + "exe";

        String testStr;
        
        DataOutputStream dos = null;

        DataOutputStream dis = null; //for testing purposes

        //generating the exe file
        try {
			dos = new DataOutputStream(new FileOutputStream(exeName)); //create exe file
            for(int i = 0; i < bytes.size(); i++) { //write byte by byte
                dos.writeByte(bytes.get(i));
                testStr = testStr + String.format("%02X ", bytes.get(i)); //for testing purposes
            }
			dos.flush();
            dos.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        return testStr;
    }
}

