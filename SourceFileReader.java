import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SourceFileReader {

    private ArrayList<Character> mnemonics = new ArrayList<Character>();
    
    // open file in read mode, and create & open input stream
    public ArrayList<Character> fileReader() throws IOException {

        try{
        BufferedReader br = new BufferedReader(new FileReader("TestInherentMnemonics.asm"));

       
        int c = 0;
        while((c=br.read())!=-1){
            char character = (char)c;
            mnemonics.add(character);
        }

        return mnemonics;

        // for(char ch: mnemonics){
        //     System.out.print(ch);
        // }

        }
        catch(FileNotFoundException e){
            System.out.print("Cannot read file");
            e.printStackTrace();
            return null;
        }
        catch(IOException error) {
            System.out.print("Cannot read file");
            return null;
        }
        
    }
    // public static void main(String[] args){
    //     SourceFileReader s = new SourceFileReader();
    //     try{s.fileReader();}
    //     catch(IOException e){
            
    //     }
    // }
    
  
  
  
  
  
  
  
  
  
  

}