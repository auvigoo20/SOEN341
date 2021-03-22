package InterfaceFiles;
import SourceFiles.*;

interface IErrorMessage {

    public String getMessage();

    public void setMessage(String message);

    public Position getPosition();

    public void setPosition(Position position);
    
}
