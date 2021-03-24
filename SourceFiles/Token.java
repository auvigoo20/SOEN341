
package SourceFiles;

import InterfaceFiles.*;

public class Token implements IToken {

    // Attributes
    private String EOL;
    private IPosition position;
    private String tokenString;

    public Token() {

    }

    public Token(String tokenString, String EOL, IPosition position) {
        this.tokenString = tokenString;
        this.EOL = EOL;
        this.position = position;
    }

    public String getEOL() {
        return this.EOL;
    }

    public void setEOL(String EOL) {
        this.EOL = EOL;
    }

    public IPosition getPosition() {
        return this.position;
    }

    public void setPosition(IPosition position) {
        this.position = position;
    }

    public String getTokenString() {
        return this.tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

}
