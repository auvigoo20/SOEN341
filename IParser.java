import java.util.ArrayList;
public interface IParser {
    
    public void requestToken(Token t);
    
    public ArrayList<LineStatement> getIntermediateRep();
}
