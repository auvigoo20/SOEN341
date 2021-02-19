//Shriman,Auvigoo, Shangirna

import java.util.ArrayList;

public class Parser {

    private ArrayList<LineStatement> IR = new ArrayList<LineStatement>();;
    private ArrayList<String> tokens;

    // default constructor
    public Parser() {

    }

    //parse LineStatements to the intermediate representation. Return IR to code generator
    public ArrayList<LineStatement> getIR(ArrayList<String> tokens) {

        this.tokens = tokens;

        String label = null;
        String mnemonic = null;
        String comment = null;
        String eol = null;
        
        for (String token : this.tokens) {

            // Check if token is a mnemonic
            if (token.charAt(0) == ' ') {
                mnemonic = token;
            }

            /*
             * else if token is label
             * 
             * else if token.charAt(0)==';' then comment
             * 
             * else if last characters of token == '\n || \r || \r\n' then oel
             */

            else if (token.contains("\n") || token.contains("\r") || token.contains("/r/n")) {

                LineStatement lineStatement = new LineStatement(label, mnemonic, comment, eol);
                IR.add(lineStatement);

                label = null;
                mnemonic = null;
                comment = null;
                eol = null;
            }
        }

        return IR;
    }

}