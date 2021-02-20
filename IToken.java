public interface IToken {
    
    public Label getLabel() ;

    public void setLabel(Label label);

    public Instruction getInstruction();

    public void setInstruction(Instruction instruction);

    public Comment getComment();

    public void setComment(Comment comment);

    public String getEOL();
    
    public void setEOL(String EOL);

    public String toString();


}
