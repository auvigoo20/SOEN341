
package InterfaceFiles;

import SourceFiles.*;

import java.util.ArrayList;

public interface ICGenerator {

    public void traverseIR(ArrayList<ILineStatement> IR, ISymbolTable symbolTable); // traverse IR using symbol table

    public int searchCode(String mnemonic, int operand);

    public void generateListing();

}
