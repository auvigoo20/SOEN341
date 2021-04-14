package Frontend;

import Components.*;






public interface IErrorReporter {

    public void record(IErrorMessage error);
    
    public void report();

    public int getNumOfReports();

}
