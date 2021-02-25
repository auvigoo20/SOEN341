public class Instruction extends Token {

    private String mnemonic;
    private int operand;

    public Instruction() {
    }

    public Instruction(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public Instruction(String mnemonic, int operand) {
        this.mnemonic = mnemonic;
        this.operand = operand;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String m) {
        mnemonic = m;
    }

    public int getOperand() {
        return operand;
    }

    public String toString() {
        return mnemonic;
    }

}
