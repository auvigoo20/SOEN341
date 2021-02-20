import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LexicalAnalyzer 
{

    // method to read file character by character and generate token
   public void readFileByLine(Parser p, SymbolTable symbolTable){
    
    try{    
        FileInputStream fis = new FileInputStream("TestInherentMnemonics.asm");
        int i;
        String mnemonic = "";


        // halt
        // add
        // sub

        while((i = fis.read()) != -1){
            // System.out.print((char)i);
         
               
                //ASCII of \n == 10. If the current character is a newline, use the built string
                if(i == 10 ){
                    
                     // make checks to see if mnemonic, label, or comment, or EOL -- TODO
                    Token t = new Token(new Instruction(mnemonic), "\n");
                    

                    //Insert mnemonic in the symbol table. This mnemonic is the same as the one that is sent as a token.
                    symbolTable.insertMnemonic(mnemonic, t);
                    
                    //Return token to the Parser
                    p.requestToken(t);

                    //System.out.println(t);
                    mnemonic = ""; // re-initialize word
                }
                
                else{
                    //concatenate characters to string
                    mnemonic += (char)i;
                    mnemonic = mnemonic.stripLeading();
                }
        }
    }
    catch(FileNotFoundException e){
        System.out.println("File not found");
        System.exit(0);
    }
    catch(IOException e){
        System.out.println("Error");
        System.exit(0);
    }     
  }
}


    
    
