package SourceFiles;
import InterfaceFiles.*;
import java.util.ArrayList;

public class ErrorReporter implements IErrorReporter{
    private ArrayList<String> errorReports;


    public ErrorReporter(){
        errorReports = new ArrayList<String>();
    }

    public void addError(String error){
        errorReports.add(error);
    }
    
    public ArrayList<String> getErrorReports(){
        return errorReports;
    }

   
}
