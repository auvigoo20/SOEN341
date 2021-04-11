package InterfaceFiles;
import SourceFiles.*;

public interface IInstruction {

    public IMnemonic getMnemonic();

    public IOperand getOperand();
    
    public IPosition getPosition();

}
