package CrossAssembler.Core;


public interface IInstruction {

    public IMnemonic getMnemonic();

    public IOperand getOperand();
    
    public IPosition getPosition();

}
