public interface ICGenerator {
    
    public void traverseIR(ArrayList IR, SymbolTable symbolTable); //traverse IR using symbol table

    public void generateListing();

}
