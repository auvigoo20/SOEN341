package SourceFiles;
import InterfaceFiles.*;

public class ErrorMessage {
    
    private String message;
    private Position position;

    public ErrorMessage(String message, Position position){
        this.message = message;
        this.position = position;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
}
