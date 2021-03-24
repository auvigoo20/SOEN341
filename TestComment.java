import SourceFiles.*;
import InterfaceFiles.*;
public class TestComment {
    
    public static void main(String[] args) {
        String comment = ";hello";
        IPosition position = new Position(1,1);

        IComment c1 = new Comment(comment, position);
        
       
        System.out.println("Test Comment");
        
        //Expected Output
        System.out.println(";hello 1 1");

        //Actual output
        System.out.println(c1.getCommentToken()+" "+c1.getPosition().getLine()+" "+c1.getPosition().getColumn());
        
    }
    
    
}
