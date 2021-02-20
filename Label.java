public class Label extends Token{
    
    String label;

    public String getLabelToken() {
        return this.label;
    }

    public void setLabelToken(String label) {
        this.label = label;
    }

    public Label(){ }

    public Label(String label){
        this.label = label;
    }


}