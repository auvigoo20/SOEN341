package CrossAssembler.Core;


public class ErrorMessage implements IErrorMessage {

    private String message;
    private IPosition position;

    public ErrorMessage(String message, IPosition position) {
        this.message = message;
        this.position = position;
    }

    public String getMessage() {
        return this.message;
    }

    public IPosition getPosition() {
        return this.position;
    }

}
