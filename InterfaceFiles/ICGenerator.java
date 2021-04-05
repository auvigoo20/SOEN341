
package InterfaceFiles;

import SourceFiles.*;

import java.util.ArrayList;

public interface ICGenerator {

    public void traverseIR(ISymbolTable symbolTable); // traverse IR using symbol table


    public void generateListing();

}
