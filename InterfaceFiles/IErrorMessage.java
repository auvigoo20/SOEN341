package InterfaceFiles;
import SourceFiles.*;

public interface IErrorMessage {

    public String getMessage();

    public void setMessage(String message);

    public IPosition getPosition();

    public void setPosition(IPosition position);
    
}
