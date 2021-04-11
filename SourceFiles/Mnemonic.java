package SourceFiles;
import InterfaceFiles.*;

public class Mnemonic implements IMnemonic{
    
    private String mnemonicString;
    private int opcode;

    public Mnemonic(String mnemonicString, int opcode){
        this.mnemonicString = mnemonicString;
        this.opcode = opcode;
    }

    public String getMnemonicString(){
        return mnemonicString;
    }

    public int getOpcode(){
        return opcode;
    }

}
