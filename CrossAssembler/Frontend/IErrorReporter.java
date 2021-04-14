package CrossAssembler.Frontend;

import CrossAssembler.Core.*;






public interface IErrorReporter {

    public void record(IErrorMessage error);
    
    public void report();

    public int getNumOfReports();

}
