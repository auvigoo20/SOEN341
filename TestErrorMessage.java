import SourceFiles.*;
import InterfaceFiles.*;


public class TestErrorMessage {
   public static void main(String[] args){
       String message = "error";
       IPosition position = new Position(1,2);

       IErrorMessage error = new ErrorMessage(message, position);

       System.out.println("Test ErrorMessage");
       // Expected output
       System.out.println("error 1 2");
       // Actual output
       System.out.println(error.getMessage()+" "+error.getPosition().getColumn()+" "+error.getPosition().getLine());

   }

}
