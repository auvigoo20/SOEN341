package InterfaceFiles;

import java.util.ArrayList;

import SourceFiles.*;


public interface IErrorReporter {

    public void addError(String error);
    
    public ArrayList<String> getErrorReports();

}
