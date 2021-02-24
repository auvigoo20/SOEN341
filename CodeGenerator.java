// step 7:  Jay, Vithu, David 

//don't know if we're allowed to use printwriter
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CodeGenerator {
    //attributes
    //private ArrayList<LineStatement> IR = new ArrayList<LineStatement>();
    //private SymbolTable symbolTable;
    private ArrayList<String> opening = new ArrayList<>(); //array containing all openings of line statement: Line Addr Code
    private ArrayList<String> closing = new ArrayList<>(); //array containing all closings of line statement: Label Mne Operand Comment
    
    //private String option; //to implement for next sprints

    //methods
    /**
     * Traverse the IR using symbol table 
     */
    public void traverseIR(ArrayList<LineStatement> intRep, SymbolTable symbols){
        int lineNbr = 1; //line number output
        int counter = 0; // counter for line number and address
        String label = "";
        String mne = "";
        String operand = "";
        String comment = "";

        String addr="";
        String code="";


        //for each mnemonic in the table
        for(int i = 0; i < intRep.size(); i++) {
            //for each line statement in IR
            for(String tableMne : symbols.gHashMap().keySet()) {
                if(tableMne.equals(intRep.get(i).getMnemonic().getMnemonic())) {    //if table mnemonic is the same as line mnemonic
                    //assign all the info into the appropriate variables
                    //label = intRep.get(i).getLabel().getLabelToken(); //for later
                    mne = intRep.get(i).getMnemonic().getMnemonic();
                    //operand = intRep.get(i).getMnemonic().getOperand1(); //might change, also this is probably not an int?
                    //comment = intRep.get(i).getComment().getCommentToken(); for later
                    addr=String.format("%04X", counter); //To hexadecimal (4bit)
                    
                    //find the opcode to the mnemonic
                   code = String.format("%02X", searchCode(mne)); //To hexadecimal (2bit)

                    opening.add(String.format("%-5s%-5s%-6s", lineNbr, addr, code)); 
                    closing.add(String.format("%-10s%-6s%-10s%-10s", label, mne, operand, comment));

                    //increment line number and address
                    lineNbr++;
                    counter++;             
                }
            } 
     } 
    } 

    /**
     * Associate a hexadecimal code to the mnemonic
     * @param mnemonic the mnemonic to look the opcode for
     * @return opcode associated to the mnemonic
     */
    public int searchCode(String mnemonic) {
        
        if(mnemonic.equals("halt"))
            return 0;
        else if(mnemonic.equals("pop"))
            return 1;
        else if(mnemonic.equals("dup"))
            return 2;
        else if(mnemonic.equals("exit"))
            return 3;
         else if(mnemonic.equals("ret"))
            return 4;
        else if(mnemonic.equals("not"))
            return 12; 
        else if(mnemonic.equals("and"))
            return 13;
        else if(mnemonic.equals("or"))
            return 14;
        else if(mnemonic.equals("xor"))
            return 15;
        else if(mnemonic.equals("neg"))
            return 10;
        else if(mnemonic.equals("inc"))
            return 11;
        else if(mnemonic.equals("dec"))
            return 12;
        else if(mnemonic.equals("add"))
            return 13;
        else if(mnemonic.equals("sub"))
            return 14; 
        else if(mnemonic.equals("mul"))
            return 15; 
        else if(mnemonic.equals("div"))
            return 16;
         else if(mnemonic.equals("rem"))
            return 17;
        else if(mnemonic.equals("shl"))
            return 18;
        else if(mnemonic.equals("shr"))
            return 19;
        else if(mnemonic.equals("teq"))
            return 26;
        else if(mnemonic.equals("tne"))
            return 27; 
        else if(mnemonic.equals("tlt"))
            return 28; 
        else if(mnemonic.equals("tgt"))
            return 29;
        else if(mnemonic.equals("tle"))
            return 30; 
        else if(mnemonic.equals("tge"))
            return 31;
        else
            return -1;
    } 

    /**
     * Generates the listing file
     */
   
     public void generateListing(){
    
        //create listing file to output to
        PrintWriter outStr = null;
        try {
            outStr = new PrintWriter(new FileOutputStream("listing.lst"));
            
        } catch(FileNotFoundException e) {
            System.out.println("Cannot create file!");
        }

        //Create format header
        outStr.println(String.format("%-5s%-5s%-6s%-10s%-6s%-10s%-10s", "Line", "Addr", "Code", "Label", "Mne", "Operand", "Comments"));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-6s%-10s%-10s]", "Line", "Addr", "Code", "Label", "Mne", "Operand", "Comments")); //for testing purposes
            
        //Generating opening and closing line statement - TO DO
        for(int i = 0; i < opening.size(); i++) {
            outStr.println(String.format("%-16s%-36s", opening.get(i), closing.get(i)));
            System.out.print(String.format("[%-16s%-36s]", opening.get(i), closing.get(i))); //for testing purposes
        }

        //close listing file
        outStr.close(); 
    }    
} 
