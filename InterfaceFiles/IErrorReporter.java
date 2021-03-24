package InterfaceFiles;

import java.util.ArrayList;

import SourceFiles.*;


public interface IErrorReporter {

    public void record(IErrorMessage error);
    
    public void report();

}
