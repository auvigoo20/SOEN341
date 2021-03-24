import InterfaceFiles.IPosition;

import SourceFiles.*;
import InterfaceFiles.*;

public class TestPosition {
    
    public static void main(String[] args){

        IPosition position = new Position(1,2);

        
        System.out.println("TestPosition");

        //Expected output
        System.out.println("1 2");
        
        //Actual output
        System.out.println(position.getColumn()+" "+ position.getLine());
    }
}
