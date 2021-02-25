import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap; //Importing all the relevant packages and libraries for use in the LexicalAnalyzer

public class LexicalAnalyzer implements ILexicalAnalyzer
{
    //Creating an object of type FileInputStream for use in opening the file.
    private FileInputStream fis; 
    
    //Creating a method for the lexicalAnalyzer, the default constructor
    public LexicalAnalyzer(){ 
    
        //Opening the input file specified
        try{
            this.fis = new FileInputStream("TestInherentMnemonics.asm"); 
        }
        
        //Checking if the file was read correctly. 
        catch(FileNotFoundException e){ 
            System.out.println("File not found");
            System.exit(0);
        }
    }
    
    //Creating a method for the lexicalAnalyzer, the parametrized constructor
    public LexicalAnalyzer(String fileName){ 

        //Opening the input file, with fileName taken as parameter
        try{
            this.fis = new FileInputStream(fileName); 
        }
        
        //Checking if the file was read correctly. 
        catch(FileNotFoundException e){ 
            System.out.println("File not found");
            System.exit(0);
        }
    }
    
   //Method to read file character by character and generate token
   public void readFileByLine(Parser p, SymbolTable symbolTable){ 
    
    try{    
        int i; //Declaring integer for use in skipping white spaces
        String mnemonic = ""; //Declaring a mnemonic string variable for use in the method


        while((i = fis.read()) != -1){
        
                //ASCII of \n == 10. If the current character is a newline, use the previously defined string
                if(i == 10 ){
                
                    //If mnemonic variable was not empty
                    if(mnemonic.length() > 0){ 
                    
                    //Creating a token object and passing in the mnemonic variable as an argument as an instruction
                    Token t = new Token(new Instruction(mnemonic), "\n"); 
                    
                    //Insert mnemonic in the symbol table. This mnemonic is the same as the one that is sent as a token.
                    symbolTable.insertMnemonic(mnemonic, t); 
                   
                    p.requestToken(t); //Return token to the Parser

                    mnemonic = ""; // re-initialize word for next loop
                    }
                    
                }
                
                //if i was not equal to ten (ie: not a newline ), concatenate characters to string
                else{  
                    mnemonic += (char)i;
                    mnemonic = mnemonic.strip();
                }
        }

        fis.close(); //close the FileInputStream object
    }
    
    //FileInputStream exception handling, if file was not read
    catch(FileNotFoundException e){ 
        System.out.println("File not found");
        System.exit(0);
    }
    
     //Expection handling if error not related to the file
    catch(IOException e){
        System.out.println("Error");
        System.exit(0);
    }     
  }
}


    
    
