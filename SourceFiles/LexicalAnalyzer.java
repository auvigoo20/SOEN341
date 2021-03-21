package SourceFiles;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap; //Importing all the relevant packages and libraries for use in the LexicalAnalyzer
import InterfaceFiles.*;

public class LexicalAnalyzer implements ILexicalAnalyzer {

    private FileInputStream fis; // Creating an object of type FileInputStream for use in opening the file.
    private static int i; // int that stores the position when reading the file
    private static String mnemonic = ""; // Declaring a mnemonic string variable for use in the method
    private SymbolTable symbolTable;
    private boolean finishScanning;

    
    // Creating a constructor for the lexicalAnalyzer, the default constructor
    public LexicalAnalyzer() {

        // Opening the input file specified
        try {
            this.fis = new FileInputStream("TestInherentMnemonics.asm");
            this.finishScanning = false;
        }

        // Checking if the file was read correctly.
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    // Creating a constructor for the lexicalAnalyzer, the parametrized constructor
    // COMMENT: should also inject symbol table
    public LexicalAnalyzer(String fileName, SymbolTable symbolTable) {

        // Opening the input file, with fileName taken as parameter
        try {
            this.fis = new FileInputStream(fileName);
            this.symbolTable = symbolTable;
            this.finishScanning = false;
        }

        // Checking if the file was read correctly.
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    // Method to generate tokens while reading the file
    public IToken getToken() {

        IToken token = null;

        // ASCII of \n == 10. If the current character is a newline, use the previously
        // defined string
        if (i == 10) {

            // If mnemonic variable was not empty
            if (mnemonic.length() > 0) {

                // Creating a token object and passing in the mnemonic variable as an argument
                // as an instruction
                token = new Token(new Instruction(mnemonic), "\n");

                mnemonic = ""; // re-initialize word for next loop
                return token;
            }

        }

        // if i was not equal to ten (ie: not a newline ), concatenate characters to
        // string
        else {
            mnemonic += (char) i;
            mnemonic = mnemonic.strip();
        }
        return token;
    }

    // Method to read file character by character and send tokens to parser and
    // symbol table
    public IToken scan() {
        
        
        IToken token =null;

        try {

            while ((i = fis.read()) != -1) {

                token = getToken();

                if (token != null) {
                    // Insert mnemonic in the symbol table. This mnemonic is the same as the one
                    // that is sent as a token.
                    symbolTable.insertMnemonic(token.getInstruction().getMnemonic(), token);

                    return token; // Return token to the Parser
                }
                
            }
            this.finishScanning = true;

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
        return token;
    }


public boolean getFinishScanning(){

    return finishScanning;
}

public void setFinishScanning(boolean fs){
finishScanning = fs;
}
}