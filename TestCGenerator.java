
import SourceFiles.*;
import InterfaceFiles.*;
import java.util.ArrayList;

public class TestCGenerator {

    public static void main(String[] args) {
        ISymbolTable table = new SymbolTable();
        ArrayList<ILineStatement> IR = new ArrayList<>();

        // add test data in test table. TODO: change params based on changes done in other classes
        table.insertMnemonic("enter.u5", new Mnemonic("enter.u5", 5));
        table.insertMnemonic("addv.u3", new Mnemonic("addv.u3", 0));
        table.insertMnemonic("stv.u3", new Mnemonic("stv.u3", 7));
        table.insertMnemonic("ldc.i3", new Mnemonic("ldc.i3", -1));
        table.insertMnemonic("ldv.u3", new Mnemonic("ldv.u3", 2));
        table.insertMnemonic(".cstring", new Mnemonic(".cstring", "\"AB\""));

        // add test data in test IR


        ILineStatement line1 = new LineStatement(null, new Instruction("enter.u5", new Operand ("5"), new Position(1,1)), new Comment("; OK, number <u5> [0..31].", new Position(2,1)));
        ILineStatement line2 = new LineStatement(null, new Instruction("addv.u3", new Operand("0"), new Position(1,1)), new Comment("; OK, number <u3> [0..7].", new Position(2,1)));
        ILineStatement line3 = new LineStatement(null, new Instruction("stv.u3", new Operand("7"), new Position(1,1)), new Comment("; OK, number <u3> [0..7].", new Position(2,1)));
        ILineStatement line4 = new LineStatement(null, new Instruction("ldc.i3", new Operand( "-1"), new Position(1,1)), new Comment("; OK, number <i3> [-4..3].", new Position(2,1)));
        ILineStatement line5 = new LineStatement(null, new Instruction("ldv.u3", new Operand( "2"), new Position(1,1)), new Comment("; OK, number <u3> [0..7].", new Position(2,1)));
        ILineStatement line6 = new LineStatement(null, new Instruction(".cstring", new Operand("\"AB\""), new Position(1,1)), new Comment("; AB has 3 bytes", new Position(2,1)));


        IR.add(line1);
        IR.add(line2);
        IR.add(line3);
        IR.add(line4);
        IR.add(line5);
        IR.add(line6);

        System.out.println("Test Code Generator");
        // Expected output on listing file
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "Line", "Addr", "Code", "Label", "Mne",
                "Operand", "Comments"));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "1", "0000", "85", "", "enter.u5", "5", "; OK, number <u5> [0..31]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "2", "0001", "98", "", "addv.u3", "0", "; OK, number <u3> [0..7]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "3", "0002", "AF", "", "stv.u3", "7", "; OK, number <u3> [0..7]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "4", "0003", "97", "", "ldc.i3", "-1", "; OK, number <i3> [-4..3]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "5", "0004", "A2", "", "ldv.u3", "2", "; OK, number <u3> [0..7]."));
        System.out.println(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "6", "0005", "41 42 00", "", ".cstring", "\"AB\"", "; AB has 3 bytes"));

        // Tested output

        CodeGenerator generate = new CodeGenerator(IR, table);
        System.out.println(generate.generateListing()); //need to test
        System.out.println();

    }

}
