package CrossAssembler.Core;

public class Token implements IToken {

    // Attributes
    private String EOL;
    private IPosition position;
    private String tokenString;
    private int opcodeOrAddress;

    public Token() {

    }

    public Token(int opcode){
        opcodeOrAddress = opcode;
    }

    public Token(String tokenString, String EOL, IPosition position) {
        this.tokenString = tokenString;
        this.EOL = EOL;
        this.position = position;
    }

    public int getOpcodeOrAddress(){
        return opcodeOrAddress;
    }

    public String getEOL() {
        return this.EOL;
    }

    public IPosition getPosition() {
        return this.position;
    }

    public String getTokenString() {
        return this.tokenString;
    }

}
