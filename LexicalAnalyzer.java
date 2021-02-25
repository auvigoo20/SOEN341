import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap; //Importing all the relevant packages and libraries for use in the LexicalAnalyzer

public class LexicalAnalyzer implements ILexicalAnalyzer
{
    private FileInputStream fis; //Creating an object of type FileInputStream for use in opening the file.


    public LexicalAnalyzer(){ //Creating a method for the lexicalAnalyzer, the default constructor

        try{
            this.fis = new FileInputStream("TestInherentMnemonics.asm"); //Opening the input file specified
        }
        catch(FileNotFoundException e){ //Checking if the file was read correctly. 
            System.out.println("File not found");
            System.exit(0);
        }
    }

    public LexicalAnalyzer(String fileName){ //Creating a method for the lexicalAnalyzer, the parametrized constructor

        try{
            this.fis = new FileInputStream(fileName); //Opening the input file, with fileName taken as parameter
        }
        catch(FileNotFoundException e){ //Checking if the file was read correctly. 
            System.out.println("File not found");
            System.exit(0);
        }
    }




  
   public void readFileByLine(Parser p, SymbolTable symbolTable){ //Method to read file character by character and generate token
    
    try{    
        int i; //Declaring integer for use in skipping white spaces
        String mnemonic = ""; //Declaring a mnemonic string variable for use in the method


        while((i = fis.read()) != -1){
        
                
                if(i == 10 ){//ASCII of \n == 10. If the current character is a newline, use the previously defined string
                    
                    if(mnemonic.length() > 0){ //If mnemonic variable was not empty

                    Token t = new Token(new Instruction(mnemonic), "\n"); // Creating a token object and passing in the mnemonic variable as an argument as an instruction

                    symbolTable.insertMnemonic(mnemonic, t); //Insert mnemonic in the symbol table. This mnemonic is the same as the one that is sent as a token.
                   
                    p.requestToken(t); //Return token to the Parser

                    mnemonic = ""; // re-initialize word for next loop
                    }
                    
                }
                
                else{   //if i was not equal to ten (ie: not a newline ), concatenate characters to string
                    mnemonic += (char)i;
                    mnemonic = mnemonic.strip();
                }
        }

        fis.close(); //close the FileInputStream object
    }
    catch(FileNotFoundException e){ //FileInputStream exception handling, if file was not read
        System.out.println("File not found");
        System.exit(0);
    }
    catch(IOException e){ //Expection handling if error not related to the file
        System.out.println("Error");
        System.exit(0);
    }     
  }
}


    
    
