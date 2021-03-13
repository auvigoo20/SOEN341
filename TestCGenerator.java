
import SourceFiles.*;
import InterfaceFiles.*;
import java.util.ArrayList;

public class TestCGenerator {

    public static void main(String[] args) {
        SymbolTable table = new SymbolTable();
        ArrayList<ILineStatement> IR = new ArrayList<>();

        // add test data in test table
        table.insertMnemonic("enter.u5", new Token(new Instruction("enter.u5", 5), "\n"));
        table.insertMnemonic("addv.u3", new Token(new Instruction("addv.u3", 0), "\n"));
        table.insertMnemonic("stv.u3", new Token(new Instruction("stv.u3", 7), "\n"));

        // add test data in test IR
        LineStatement line1 = new LineStatement(new Label(), new Instruction("enter.u5", 5), new Comment("; OK, number <u5> [0..31]."), "\n");
        LineStatement line2 = new LineStatement(new Label(), new Instruction("addv.u3", 0), new Comment("; OK, number <u3> [0..7]."), "\n");
        LineStatement line3 = new LineStatement(new Label(), new Instruction("stv.u3", 7), new Comment("; OK, number <u3> [0..7]."), "\n");

        IR.add(line1);
        IR.add(line2);
        IR.add(line3);

        System.out.println("Test Code Generator");
        // Expected output on listing file
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "Line", "Addr", "Code", "Label", "Mne",
                "Operand", "Comments"));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "1", "0000", "75", "", "enter.u5", "5", "; OK, number <u5> [0..31]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "2", "0001", "98", "", "addv.u3", "0", "; OK, number <u3> [0..7]."));
        System.out.println(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "3", "0002", "AF", "", "stv.u3", "7", "; OK, number <u3> [0..7]."));

        // Tested output

        CodeGenerator generate = new CodeGenerator();
        generate.traverseIR(IR, table);
        generate.generateListing();
        System.out.println();

    }

}
