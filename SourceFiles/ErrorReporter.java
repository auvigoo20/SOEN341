package SourceFiles;

import InterfaceFiles.*;
import java.util.ArrayList;

public class ErrorReporter implements IErrorReporter {

    // should have an array of errorMessage objects
    private ArrayList<IErrorMessage> errorReports;

    public ErrorReporter() {
        errorReports = new ArrayList<IErrorMessage>();
    }

    public void record(IErrorMessage error) {
        errorReports.add(error);
    }

    public void report() {
        for (IErrorMessage e : errorReports) {
            System.out.print(e.getMessage());
            System.out.println(" @column: " + e.getPosition().getColumn() + " @line: " + e.getPosition().getLine());
            // System.out.println();
        }
    }

    public ArrayList<IErrorMessage> getErrorReports(){
        return errorReports;
    }

}
