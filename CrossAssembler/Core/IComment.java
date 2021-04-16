package CrossAssembler.Core;


public interface IComment extends IToken{
    
    public String getCommentToken();
    
    public IPosition getPosition();

}
