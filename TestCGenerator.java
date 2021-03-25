
import SourceFiles.*;
import InterfaceFiles.*;
import java.util.ArrayList;

public class TestCGenerator {

    public static void main(String[] args) {
        ISymbolTable table = new SymbolTable();
        ArrayList<ILineStatement> IR = new ArrayList<>();

        // add test data in test table
        table.insertMnemonic("enter.u5", new Token("enter.u5", "", new Position(1,1)));
        table.insertMnemonic("addv.u3", new Token("addv.u3", "", new Position(2,1)));
        table.insertMnemonic("stv.u3", new Token("stv.u3", "", new Position(3,1)));
        table.insertMnemonic("ldc.i3", new Token("ldc.i3", "newLine", new Position(4,1)));

        // add test data in test IR


        ILineStatement line1 = new LineStatement(null, new Instruction("enter.u5", "5", new Position(1,1)), new Comment("; OK, number <u5> [0..31].", new Position(2,1)));
        ILineStatement line2 = new LineStatement(null, new Instruction("addv.u3", "0", new Position(1,1)), new Comment("; OK, number <u3> [0..7].", new Position(2,1)));
        ILineStatement line3 = new LineStatement(null, new Instruction("stv.u3", "7", new Position(1,1)), new Comment("; OK, number <u3> [0..7].", new Position(2,1)));
        ILineStatement line4 = new LineStatement(null, new Instruction("ldc.i3", "-2", new Position(1,1)), new Comment("; OK, number <i3> [-4..3].", new Position(2,1)));


        IR.add(line1);
        IR.add(line2);
        IR.add(line3);
        IR.add(line4);

        System.out.println("Test Code Generator");
        // Expected output on listing file
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "Line", "Addr", "Code", "Label", "Mne",
                "Operand", "Comments"));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "1", "0000", "85", "", "enter.u5", "5", "; OK, number <u5> [0..31]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "2", "0001", "98", "", "addv.u3", "0", "; OK, number <u3> [0..7]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "3", "0002", "AF", "", "stv.u3", "7", "; OK, number <u3> [0..7]."));
        System.out.println(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "4", "0003", "96", "", "ldc.i3", "-2", "; OK, number <i3> [-4..3]."));

        // Tested output

        CodeGenerator generate = new CodeGenerator();
        generate.traverseIR(IR, table);
        generate.generateListing();
        System.out.println();

    }

}
