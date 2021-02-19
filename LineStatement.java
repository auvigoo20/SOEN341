public class LineStatement {
    
    private String label;
    private String mnemonic;
    private String comment;
    private String eol;

    public LineStatement(){

    }

    public LineStatement(String label, String mnemonic, String comment, String eol){
        this.label = label;
        this.mnemonic = mnemonic;
        this.comment = comment;
        this.eol = eol;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }

    
    public String getMnemonic(){
        return mnemonic;
    }

    public void setMnemonic(String mnemonic){
        this.mnemonic = mnemonic;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
    
    public String getEOL(){
        return eol;
    }

    public void setEOL(String eol){
        this.eol = eol;
    }
}
