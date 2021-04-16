package CrossAssembler.Core;


public interface IOperand extends IToken{

    public boolean isOperandNumber();

    public int getOperandNumber();

    public String getOperandString();
    
}
