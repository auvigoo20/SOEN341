// step 7:  Jay, Vithu, David 

//don't know if we're allowed to use printwriter
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class CodeGenerator {
    //attributes
    private IntermediateRepresentation IR;
    private Object symbolTable; //Not sure if symbolTable is object
    //private String option;

    //methods
    /**
     * Traverse the IR using symbol table 
     */
    public void traverseIR(IntermediateRepresentation IR, Object symbolTable){
        //don't know how an IR and symbol table look like, need example from prof
    } 

    /**
     * Generates the listing file
     */
   
     public void generateListing(){
    
        //create listing file to output to
        PrintWriter outStr = null;
        try {
            outStr = new PrintWriter(new FileOutputStream("listing.txt"));
        } catch(FileNotFoundException e) {
            System.out.println("Cannot read file!");
        }

        //Create format header
        outStr.println("Line Addr Code Label Mne Operand Comment");

        //Generating opening line statement - TO DO

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
