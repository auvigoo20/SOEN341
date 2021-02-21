import java.util.ArrayList;
public class TestParser {
    
    public static void main(String[] args){


        //Simulate 3 tokens being read by the lexical analyzer
        Token t1 = new Token(new Instruction("halt"), "\n");
        Token t2 = new Token(new Instruction("pop"), "\n");
        Token t3 = new Token(new Instruction("dup"), "\n");

        Parser parser = new Parser();

        //Simulate the lexical analyzer sending 3 tokens to the parser
        parser.requestToken(t1);
        parser.requestToken(t2);
        parser.requestToken(t3);

        //Parser creating line statements from the tokens and inserting them into
        //the intermediate representation
        ArrayList<LineStatement> IR = parser.getIntermediateRep();

        LineStatement l0 = IR.get(0);
        LineStatement l1 = IR.get(1);
        LineStatement l2 = IR.get(2);

        //Expected Output
        System.out.println("Test Parser");
        System.out.println("[halt][pop][dup]");


        //Actual output
        System.out.print(l0);
        System.out.print(l1);
        System.out.print(l2);
        System.out.println();



        
    




    }

}
