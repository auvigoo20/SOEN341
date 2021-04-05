// step 7:  Jay, Vithu, David 
package SourceFiles;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import InterfaceFiles.*;

public class CodeGenerator implements ICGenerator {
    // Class attributes
    private ArrayList<String> opening = new ArrayList<>(); // array containing all openings of line statement: Line Addr
                                                           // Code
    private ArrayList<String> closing = new ArrayList<>(); // array containing all closings of line statement: Label Mne
                                                           // Operand Comment

    // private String option; //to implement for next sprints

    private IIntermediateRepresentation IR;

    public CodeGenerator(IIntermediateRepresentation IR){
        this.IR = IR;
    }

    // methods
    /**
     * Traverse the IR using the symbol table
     * 
     * @param intRep  the IR produced
     * @param symbols the symbol table produced
     */
    public void traverseIR(ISymbolTable symbols) {
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

            //Get the position from either the mnemonic or the comment (doesn't matter which since only line number is needed)
            if (intRep.get(i).getMnemonic() != null){
                position = intRep.get(i).getMnemonic().getPosition();
            }

            if (intRep.get(i).getComment() != null) {
                position = intRep.get(i).getComment().getPosition();
            }

            //Add empty lines (except for line number and address) if the .asm contains empty lines
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
            if (intRep.get(i).getMnemonic() != null) {
                
                // for each symbol in the table
                for (String tableMne : symbols.getSymbolTable().keySet()) {

                    if (tableMne.equals(intRep.get(i).getMnemonic().getMnemonic())) { // if table mnemonic is the same as line mnemonic

                        // assign all the info into the appropriate variables
                        // label = intRep.get(i).getLabel().getLabelToken(); //for later
                        mne = intRep.get(i).getMnemonic().getMnemonic();

                        //first check if operand is a number or a string
                        if(intRep.get(i).getMnemonic().getOperand().isOperandNumber()){
                            operandNumber = intRep.get(i).getMnemonic().getOperand().getOperandNumber();
                        }
                        else{
                            operandString = intRep.get(i).getMnemonic().getOperand().getOperandString();
                        }

                        // find the opcode to the mnemonic
                        if(mne.equals(".cstring")) { //evaluate opcode for cstring
                            String string=operandString;
                            String cstring;
                            if(string.length() > 4) //if there are more than 3 characters to evaluate (1 is a quotation mark)
                                cstring = string.substring(string.indexOf("\"")+1, 4); //ignore 1st quotation mark
                            else
                                cstring = string.substring(string.indexOf("\"")+1, string.lastIndexOf("\"")); //ignore quotation marks

                            char[] ch=cstring.toCharArray();

                            StringBuilder builder= new StringBuilder(); 
                            for(char c: ch) { //evaluate the hex for each char
                                String Hex=String.format("%H", c); 
                                builder.append(Hex+" "); 
                            }
                            code = builder.toString() + "00";
                        }
                        else { //opcode for every other mnemonic
                            int opcode = searchCode(mne, operandNumber);
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

            addr = String.format("%04X", counter); // Convert decimal counter to hexadecimal (4bit)

            opening.add(String.format("%-5s%-5s%-6s", lineNbr, addr, code));

            if (operandString == null && operandNumber == Integer.MAX_VALUE) {
                closing.add(String.format("%-10s%-10s%-10s%-10s", label, mne, "", comment));
            }
            else if(operandString != null){
                closing.add(String.format("%-10s%-10s%-10s%-10s", label, mne, operandString, comment));
            }
            else if(operandNumber != Integer.MAX_VALUE){
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
     * Associate a hexadecimal code to the mnemonic
     * 
     * @param mnemonic the mnemonic to look the opcode for
     * @return opcode associated to the mnemonic
     */
    public int searchCode(String mnemonic, int operand) {

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
            
            int number = operand; //for next sprint, may change; operand connected to bits; assume error handler takes care of non-integer operands
		    int opcode = (number > 15) ? 0x70 : 0x80;
			opcode = opcode | number;
		
		    return opcode;


        }

        else if (mnemonic.equals("ldc.i3")) {
          
            int number = operand; //for next sprint, may change; operand connected to bits; assume error handler takes care of non-integer operands
            int opcode = 0x90;
            
            if(number >= 0)
                opcode = opcode | number;
            else
                opcode = opcode | (number & 0x07);
            
            return opcode;
        
        }

        else if (mnemonic.equals("addv.u3")) {
            

            int number = operand; //for next sprint, may change; operand connected to bits; assume error handler takes care of non-integer operands
		    int opcode = 0x98;
			opcode = opcode | number;

            return opcode; 

        }

        else if (mnemonic.equals("ldv.u3")) {
          

            int number = operand; //for next sprint, may change; operand connected to bits; assume error handler takes care of non-integer operands
		    int opcode = 0xA0; 
			opcode = opcode | number; 

            return opcode;

        }

        else if (mnemonic.equals("stv.u3")) {
         

            int number = operand; //for next sprint, may change; operand connected to bits; assume error handler takes care of non-integer operands
		    int opcode = 0xA8; 
			opcode = opcode | number;

            return opcode; 
        }

        else
            return -1;
    }

    /**
     * Generates the listing file
     */

    public void generateListing() {

        // create listing file to output to
        PrintWriter outStr = null;
        try {
            outStr = new PrintWriter(new FileOutputStream("listing.lst"));

        } catch (FileNotFoundException e) {
            System.out.println("Cannot create file!");
        }

        // Create format header
        outStr.println(String.format("%-5s%-5s%-6s%-10s%-10s%-10s%-10s", "Line", "Addr", "Code", "Label", "Mne",
                "Operand", "Comments"));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "Line", "Addr", "Code", "Label", "Mne",
                "Operand", "Comments")); // for testing purposes

        // Generating opening and closing line statement
        for (int i = 0; i < opening.size(); i++) {
            outStr.println(String.format("%-16s%-40s", opening.get(i), closing.get(i)));
            System.out.print(String.format("[%-16s%-40s]", opening.get(i), closing.get(i))); // for testing purposes
        }

        // close listing file
        outStr.close();
    }
}
