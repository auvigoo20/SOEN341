import SourceFiles.*;
import InterfaceFiles.*;

public class TestErrorReporter {
    public static void main(String args[]){
        String message = "error";
        IPosition position = new Position(1,2);
        IErrorMessage error = new ErrorMessage(message, position);
        IErrorReporter errorReporter = new ErrorReporter();

        System.out.println("Test ErrorReporter");
        
        // Expected
        System.out.println("error @column: 1 @line: 2");
        
        //actual
            // record
        errorReporter.record(error);
            // report
        errorReporter.report();
    }
}
