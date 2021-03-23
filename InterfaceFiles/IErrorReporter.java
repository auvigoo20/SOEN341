package InterfaceFiles;

import java.util.ArrayList;

import SourceFiles.*;


public interface IErrorReporter {

    public void record(ErrorMessage error);
    
    public void report();

}
