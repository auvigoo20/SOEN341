package Components;

public class Mnemonic extends Token implements IMnemonic{
    
    private String mnemonicString;
    private String cstringOpcode;
    private int opcode;

    public Mnemonic(String mnemonicString, int opcode){
        this.mnemonicString = mnemonicString;
        this.opcode = opcode;
    }

    //This constructor is ONLY for .cstring instructions. Ex: mnemonicString = ".cstring", opcode = "D0 01 00"
    public Mnemonic(String cstringMnemonic, String cstringOpcode){
        this.mnemonicString = cstringMnemonic;
        this.cstringOpcode = cstringOpcode;
        this.opcode = Integer.MAX_VALUE;
    }

    public String getMnemonicString(){
        return mnemonicString;
    }

    public String getCStringOpcode(){
        return cstringOpcode;
    }

    public int getOpcode(){
        return opcode;
    }

}
