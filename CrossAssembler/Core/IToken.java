package CrossAssembler.Core;

public interface IToken {

    public String getEOL();

    public IPosition getPosition();

    public String getTokenString();

    public int getOpcodeOrAddress();
}
