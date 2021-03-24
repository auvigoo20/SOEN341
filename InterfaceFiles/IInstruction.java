package InterfaceFiles;
import SourceFiles.*;

public interface IInstruction {

    public String getInstructionType();

    public void setInstructionType(String mnemonic);

    public String getMnemonic();

    public void setMnemonic(String m);

    public String getOperand();
    
    public IPosition getPosition();

}
