import java.util.ArrayList;

public interface ICGenerator {
    
    public void traverseIR(ArrayList<LineStatement> IR, SymbolTable symbolTable); //traverse IR using symbol table

    public void generateListing();

}
