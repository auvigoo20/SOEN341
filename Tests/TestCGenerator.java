package Tests;
import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;
import java.util.ArrayList;

public class TestCGenerator {

    public static void main(String[] args) {
        ISymbolTable table = new SymbolTable();
        IntermediateRepresentation IR = new IntermediateRepresentation();

        // add test data in test table. TODO: change params based on changes done in other classes
        table.insertMnemonic("enter.u5", new Mnemonic("enter.u5", 133));
        table.insertMnemonic("addv.u3", new Mnemonic("addv.u3", 152));
        table.insertMnemonic("stv.u3", new Mnemonic("stv.u3", 175));
        table.insertMnemonic("ldc.i3", new Mnemonic("ldc.i3", 151));
        table.insertMnemonic("ldv.u3", new Mnemonic("ldv.u3", 162));
        table.insertMnemonic(".cstring", new Mnemonic(".cstring", "\"AB\""));

        // add test data in test IR


        ILineStatement line1 = new LineStatement(null, new Instruction(new Mnemonic("enter.u5", 133), new Operand ("5"), new Position(1,1)), new Comment("; OK, number <u5> [0..31].", new Position(2,1))); //label position
        ILineStatement line2 = new LineStatement(null, new Instruction(new Mnemonic("addv.u3", 152), new Operand("0"), new Position(1,1)), new Comment("; OK, number <u3> [0..7].", new Position(2,1)));
        ILineStatement line3 = new LineStatement(null, new Instruction(new Mnemonic("stv.u3", 175), new Operand("7"), new Position(1,1)), new Comment("; OK, number <u3> [0..7].", new Position(2,1)));
        ILineStatement line4 = new LineStatement(null, new Instruction(new Mnemonic("ldc.i3", 151), new Operand( "-1"), new Position(1,1)), new Comment("; OK, number <i3> [-4..3].", new Position(2,1)));
        ILineStatement line5 = new LineStatement(null, new Instruction(new Mnemonic("ldv.u3", 162), new Operand( "2"), new Position(1,1)), new Comment("; OK, number <u3> [0..7].", new Position(2,1)));
        //ILineStatement line6 = new LineStatement(null, new Instruction(".cstring", new Operand("\"AB\""), new Position(1,1)), new Comment("; AB has 3 bytes", new Position(2,1)));


        IR.addLineStatement(line1);
        IR.addLineStatement(line2);
        IR.addLineStatement(line3);
        IR.addLineStatement(line4);
        IR.addLineStatement(line5);
        //IR.addLineStatement(line6);

        System.out.println("Test Code Generator");
        // Expected output on listing file
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "Line", "Addr", "Code", "Label", "Mne",
                "Operand", "Comments"));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "1", "0000", "85", "", "enter.u5", "5", "; OK, number <u5> [0..31]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "2", "0001", "98", "", "addv.u3", "0", "; OK, number <u3> [0..7]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "3", "0002", "AF", "", "stv.u3", "7", "; OK, number <u3> [0..7]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "4", "0003", "97", "", "ldc.i3", "-1", "; OK, number <i3> [-4..3]."));
        System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "5", "0004", "A2", "", "ldv.u3", "2", "; OK, number <u3> [0..7]."));
        //System.out.print(String.format("[%-5s%-5s%-6s%-10s%-10s%-10s%-10s]", "6", "0005", "41 42 00", "", ".cstring", "\"AB\"", "; AB has 3 bytes"));
        System.out.println("[85 98 AF 97 A2 ]");

        // Tested output

        CodeGenerator generate = new CodeGenerator("listing.asm", IR, table);
        System.out.print(generate.generateListing()); //need to test
        System.out.println(generate.generateExe());

    }

}
