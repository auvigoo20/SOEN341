import java.util.ArrayList;

public class TestCGenerator {

    public static void main(String[] args) {
        SymbolTable table = new SymbolTable();
        ArrayList<LineStatement> IR = new ArrayList<>();

        //add test data in test table
        table.insertMnemonic("halt", new Token(new Instruction("halt"), "\n"));
        table.insertMnemonic("pop", new Token(new Instruction("pop"), "\n"));
        table.insertMnemonic("dup", new Token(new Instruction("dup"), "\n"));

        //add test data in test IR
        LineStatement line1 = new LineStatement(new Label(), new Instruction("halt"), new Comment(), "\n");
        LineStatement line2 = new LineStatement(new Label(), new Instruction("pop"), new Comment(), "\n");
        LineStatement line3 = new LineStatement(new Label(), new Instruction("dup"), new Comment(), "\n");

        IR.add(line1);
        IR.add(line2);
        IR.add(line3);

        System.out.println("Expected output on listing file:\n");
        System.out.println("Line \t Addr \t Code \t Label \t Mne \t Operand \t Comments");
        System.out.println("1 \t 0000 \t 00 \t  \t halt \t ");
        System.out.println("2 \t 0001 \t 01 \t  \t pop \t ");
        System.out.println("3 \t 0003 \t 02 \t  \t dup \t \n");

        CodeGenerator generate = new CodeGenerator();
        generate.traverseIR(IR, table);
        generate.generateListing();

    }
    
}
