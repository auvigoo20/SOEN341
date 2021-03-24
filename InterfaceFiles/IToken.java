package InterfaceFiles;

import SourceFiles.*;

public interface IToken {

    public String getEOL();

    public void setEOL(String EOL);

    public IPosition getPosition();

    public void setPosition(IPosition position);

    public String getTokenString();

    public void setTokenString(String tokenString);
}
