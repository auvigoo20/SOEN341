package SourceFiles;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap; //Importing all the relevant packages and libraries for use in the LexicalAnalyzer
import InterfaceFiles.*;

public class LexicalAnalyzer implements ILexicalAnalyzer {

    private FileInputStream fis; // Creating an object of type FileInputStream for use in opening the file.
    private static int i; // int that stores the position when reading the file
    private static String unknownString = ""; // Declaring a mnemonic string variable for use in the method
    private SymbolTable symbolTable;
    private static int tokenLine=0;
    private static int tokenColumn=1;
    private final String[] inherentMnemonics = {"halt", "pop", "dup", "exit","ret","not","and","or","xor","neg","inc","dec","add","sub","mul","div","rem","shl","shr","teq","tne","tlt","tgt","tle","tge"};
    private final String[] immediateMnemonics = {"enter.u5", "ldc.i3", "addv.u3", "ldv.u3", "stv.u3"};


    // Creating a constructor for the lexicalAnalyzer, the default constructor
    public LexicalAnalyzer() {

        // Opening the input file specified
        try {
            this.fis = new FileInputStream("TestInherentMnemonics.asm");
            symbolTable = new SymbolTable();
        }

        // Checking if the file was read correctly.
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    // Creating a constructor for the lexicalAnalyzer, the parametrized constructor
    // COMMENT: should also inject symbol table
    public LexicalAnalyzer(String fileName) {

        // Opening the input file, with fileName taken as parameter
        try {
            this.fis = new FileInputStream(fileName);
            symbolTable = new SymbolTable();

        }

        // Checking if the file was read correctly.
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    // Method to generate tokens while reading the file
    public Token generateToken() {

        Token token = null;

/** Mnemonic  oper     comment
 *  enter.u5  0        ; OK, number <u5> [0..31].           
 *  ente6.u5  15        ; OK, number <u5> [0..31].
 */
try{

        if (i ==32){
            
            if (Arrays.asList(inherentMnemonics).contains(unknownString.strip())){

                token = new Token(null, new Instruction(unknownString), null, "", new Position(tokenColumn, tokenLine), true);
                
            }
            
            else if (Arrays.asList(immediateMnemonics).contains(unknownString.strip())){
                while ((i = fis.read()) == 32);
                String operandString = "";
                while ((i = fis.read()) != 32){
                    operandString += (char) i;
                    if(i == 10){
                        tokenLine++;
                    }
                }
                int operandInt = Integer.parseInt(operandString);

                token = new Token(null, new Instruction(unknownString,operandInt),null,"",new Position(tokenColumn, tokenLine), true);
            }
            else if (unknownString.charAt(0)==';'){
                while ((i = fis.read()) != 10){
                    unknownString += (char) i;
                }
                token = new Token(null,null,new Comment(unknownString),"\n",new Position(tokenColumn, tokenLine), true);
                tokenLine++;
            }
            else{
                //generate error
            }

            tokenColumn++;
            
        }

        else{
            unknownString += (char) i;
        }

        if(i == 10){
            tokenLine++;
        }









        // ASCII of \n == 10. If the current character is a newline, use the previously
        // defined string
        // if (i == 10) {

        //     // If mnemonic variable was not empty
        //     if (mnemonic.length() > 0) {

        //         // Creating a token object and passing in the mnemonic variable as an argument
        //         // as an instruction
        //         token = new Token(new Instruction(mnemonic), "\n");

        //         mnemonic = ""; // re-initialize word for next loop
        //         return token;
        //     }

        // }

        // // if i was not equal to ten (ie: not a newline ), concatenate characters to
        // // string
        // else {
        //     mnemonic += (char) i;
        //     mnemonic = mnemonic.strip();
        // }

        
        
}
catch(IOException e){

}
return token;
    }

    // Method to read file character by character and send tokens to parser and
    // symbol table
    public void readFileByLine(IParser p) {

        try {

            while ((i = fis.read()) != -1) {

                Token token = generateToken();

                if (token != null) {
                    // Insert mnemonic in the symbol table. This mnemonic is the same as the one
                    // that is sent as a token.
                    symbolTable.insertMnemonic(token.getInstruction().getMnemonic(), token);

                    p.requestToken(token); // Return token to the Parser
                }

            }

            fis.close(); // close the FileInputStream object
        }

        // FileInputStream exception handling, if file was not read
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        // Expection handling if error not related to the file
        catch (IOException e) {
            System.out.println("Error");
            System.exit(0);
        }

    }


    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

}
