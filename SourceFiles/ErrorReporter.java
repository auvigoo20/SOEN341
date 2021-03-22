package SourceFiles;
import InterfaceFiles.*;
import java.util.ArrayList;

public class ErrorReporter implements IErrorReporter{

    // should have an array of errorMessage objects
     private ArrayList<ErrorMessage> errorReports;

    public ErrorReporter(){
        errorReports = new ArrayList<ErrorMessage>();
    }

    public void record(ErrorMessage error){
        errorReports.add(error);
    }
    

    public void report(){
        for(ErrorMessage e : errorReports){
            System.out.println(e.getMessage());
            System.out.println("@column: "+ e.getPosition().getColumn() + " @line: "+ e.getPosition().getLine());
            System.out.println();
        }
    }

   
}
