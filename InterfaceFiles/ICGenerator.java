
package InterfaceFiles;

import SourceFiles.*;

import java.util.ArrayList;

public interface ICGenerator {

    public void traverseIR(ArrayList<ILineStatement> IR, ISymbolTable symbolTable); // traverse IR using symbol table

    public int searchCode(String mnemonic, String operand); //searches the hexadecimal code in integers for a given mnemonic and operand

    public void generateListing();

}
