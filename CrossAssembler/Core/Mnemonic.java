package CrossAssembler.Core;

import java.util.Arrays;

public class Mnemonic extends Token implements IMnemonic{
    
    private String mnemonicString;
    private String cstringOpcode;
    private int size;
    private int opcode;
    private final String[] immediateMnemonics = { "enter.u5", "ldc.i3", "addv.u3", "ldv.u3", "stv.u3" };
    private final String[] inherentMnemonics = { "halt", "pop", "dup", "exit", "ret", "not", "and", "or", "xor", "neg",
    "inc", "dec", "add", "sub", "mul", "div", "rem", "shl", "shr", "teq", "tne", "tlt", "tgt", "tle", "tge" };

    private final String[] relativeMnemonics = { "br.i8", "brf.i8", "ldc.i8", "ldv.u8", "stv.u8", "lda.i16" };


    public Mnemonic(String mnemonicString, int opcode){
        this.mnemonicString = mnemonicString;
        this.opcode = opcode;
        determineMnemonicSize(mnemonicString);
    }

    //This constructor is ONLY for .cstring instructions. Ex: mnemonicString = ".cstring", opcode = "D0 01 00"
    public Mnemonic(String cstringMnemonic, String cstringOpcode){
        this.mnemonicString = cstringMnemonic;
        this.cstringOpcode = cstringOpcode;
        determineCStringSize(cstringOpcode);
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

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size = size;
    }

    private void determineMnemonicSize(String mnemonicString){

        if(Arrays.asList(immediateMnemonics).contains(mnemonicString) || Arrays.asList(inherentMnemonics).contains(mnemonicString)){
            size = 1;
        }
        else if(Arrays.asList(relativeMnemonics).contains(mnemonicString)){
            if(mnemonicString.contains("i8")){
                size = 1;
            }
            else if(mnemonicString.contains("i16")){
                size = 2;
            }
        }
    }

    private void determineCStringSize(String cStringOpcode){
        String[] hexCodes = cStringOpcode.split(" ");
        size = hexCodes.length;
    }

    // public static void main(String[] args){
    //     IMnemonic mnemonic1 = new Mnemonic("halt", 9);
    //     System.out.println(mnemonic1.getSize());

    //     System.out.println();
    //     IMnemonic mnemonic2 = new Mnemonic("cstring", "A2 F4");
    //     System.out.println(mnemonic2.getSize());
    // }

}
