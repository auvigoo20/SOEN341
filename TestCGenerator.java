
import SourceFiles.*;
import InterfaceFiles.*;
import java.util.ArrayList;

public class TestCGenerator {

    public static void main(String[] args) {
        SymbolTable table = new SymbolTable();
        ArrayList<ILineStatement> IR = new ArrayList<>();

        // add test data in test table
        table.insertMnemonic("halt", new Token(new Instruction("halt"), "\n"));
        table.insertMnemonic("not", new Token(new Instruction("not"), "\n"));
        table.insertMnemonic("dup", new Token(new Instruction("dup"), "\n"));

        // add test data in test IR
        LineStatement line1 = new LineStatement(new Label(), new Instruction("halt"), new Comment(), "\n");
        LineStatement line2 = new LineStatement(new Label(), new Instruction("not"), new Comment(), "\n");
        LineStatement line3 = new LineStatement(new Label(), new Instruction("dup"), new Comment(), "\n");

        IR.add(line1);
        IR.add(line2);
        IR.add(line3);

        System.out.println("Test Code Generator");
        // Expected output on listing file
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-6s%-10s%-10s]", "Line", "Addr", "Code", "Label", "Mne",
                "Operand", "Comments"));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-6s%-10s%-10s]", "1", "0000", "00", "", "halt", "", ""));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-6s%-10s%-10s]", "2", "0001", "0C", "", "not", "", ""));
        System.out.println(String.format("[%-5s%-5s%-6s%-10s%-6s%-10s%-10s]", "3", "0002", "02", "", "dup", "", ""));

        // Tested output

        CodeGenerator generate = new CodeGenerator();
        generate.traverseIR(IR, table);
        generate.generateListing();
        System.out.println();

    }

}
