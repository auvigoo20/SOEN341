package InterfaceFiles;
import SourceFiles.*;

public interface IInstruction {

    public String getInstructionType();

    public String getMnemonic();

    public IOperand getOperand();
    
    public IPosition getPosition();

}
