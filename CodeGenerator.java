// step 7:  Jay, Vithu, David 

//don't know if we're allowed to use printwriter
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class CodeGenerator {
    //attributes
    private ArrayList<LineStatement> IR = new ArrayList<LineStatement>();
    private SymbolTable symbolTable;
    private ArrayList<String> opening;
    private ArrayList<String> closing;
    
    //private String option;

    //methods
    /**
     * Traverse the IR using symbol table 
     */
    public void traverseIR(ArrayList IR, SymbolTable symbols){
        int i = 0;
        int line = 1;
        int addr = 0;
        int code = 0;
        String label = "";
        String mne = "";
        String operand = "";

        //want to traverse using symbol table and assign to opening and closing of line, but difficult when it is not in array form
        for(String name : symbolTable.gHashMap().keySet()) {
            
            
            
            //VERY WRONG CODE; DONT USE; PSEUDOCODE
            //line++; 
            //addr++;
            /*
            symbol mnemonic

           
            
            for(IR)
                symbol mnemonic = IR mnemonic Instruction
                if it is do
                    opening[i] = line addr code
                    closing[i] = Label Mne Operand Comment
                    i++
                    line++
                    addr++   
            */
            
   

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
            outStr = new PrintWriter(new FileOutputStream ("listing.lst"));
            
        } catch(FileNotFoundException e) {
            System.out.println("Cannot create file!");
        }

        //Create format header
        outStr.println("Line \t Addr \t Code \t Label \t Mne \t Operand \t Comment");
            
        //Generating opening line statement - TO DO
        String addr=null;
        int i=0;
             for(int line=1; line<=26; line++){ //26 is placeholder for testing
                    
                      addr=String.format("%04X", i);
                      i++;          
                      outStr.println(line+" "+addr);
             } 

        
        



        //generate closing line statement - TO DO

        //close listing file
        outStr.close(); 
    } 

    /**
     * Generates the executable file, to do later
    void generateExecutable(){} 
    */  

    /*
    public static void main(String[] args){
        CodeGenerator code = new CodeGenerator();
        code.generateListing();
    } 
    */   
} 
