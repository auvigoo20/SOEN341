package CrossAssembler.Core;


public interface IMnemonic extends IToken{

    public String getMnemonicString();

    public int getOpcode();

    public String getCStringOpcode();

    public int getSize();

    public void setSize(int size);
}
