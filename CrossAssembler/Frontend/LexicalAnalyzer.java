package CrossAssembler.Frontend;

import CrossAssembler.Core.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class LexicalAnalyzer implements ILexicalAnalyzer {

    private FileInputStream fis; // Creating an object of type FileInputStream for use in opening the file.
    private static int i; // int that stores the position when reading the file
    private static String unknownString = ""; // String to hold the token being created
    private ISymbolTable symbolTable; // symbol table object
    private IErrorReporter errorReporter;

    private static int tokenLine = 1;
    private static int tokenColumn = 1;
    private final String[] inherentMnemonics = { "halt", "pop", "dup", "exit", "ret", "not", "and", "or", "xor", "neg",
            "inc", "dec", "add", "sub", "mul", "div", "rem", "shl", "shr", "teq", "tne", "tlt", "tgt", "tle", "tge" };
    private final String[] immediateMnemonics = { "enter.u5", "ldc.i3", "addv.u3", "ldv.u3", "stv.u3" };
    private final String[] relativeMnemonics = { "br.i8", "brf.i8", "ldc.i8", "ldv.u8", "stv.u8", "lda.i16" };
    private boolean finishScanning;

    public LexicalAnalyzer(String fileName, ISymbolTable symbolTable, IErrorReporter errorReporter) {

        // Checking if the file was read correctly.
        try {
            this.fis = new FileInputStream(fileName);
            this.symbolTable = symbolTable; // constructor injection of symbol table.
            this.finishScanning = false;
            this.errorReporter = errorReporter; // constructor injection of error reporter
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    // Method to sequentially generate tokens while reading the file
    private IToken getToken() {

        IToken token = null;

        try {
            // if the token is a comment (starts with a ;), build the string until reaching
            // end of line
            if (i == ';') {
                unknownString += (char) i;
                while ((i = fis.read()) != '\n') {
                    unknownString += (char) i;
                }
                unknownString = unknownString.trim();
                token = new Token(unknownString, "newLine", new Position(tokenColumn, tokenLine));
                tokenLine++;
                tokenColumn = 1;
                unknownString = "";
            }

            // if the token does not contain the EOL marker
            else if ((i == ' ') && unknownString.trim().length() > 0) {

                unknownString = unknownString.trim();
                // check for invalid characters if it's not a comment
                if (hasInvalidChar(unknownString)) {
                    String error = "Error: Invalid character in " + unknownString;
                    errorReporter.record(new ErrorMessage(error, new Position(tokenColumn, tokenLine)));
                }

                // check for \n or \r
                else if (unknownString.contains("\n") || unknownString.contains("\r")) {
                    String error = "Error: EOL in string in " + unknownString;
                    errorReporter.record(new ErrorMessage(error, new Position(tokenColumn, tokenLine)));

                }
                // no lexical errors
                else {
                    token = new Token(unknownString, "", new Position(tokenColumn, tokenLine));
                }
                tokenColumn++; // go to next column
                unknownString = ""; // re-initialize string to build next token

            }

            // if the token contains the EOL marker
            else if ((i == 10)) {
                unknownString = unknownString.trim();
                if (unknownString.length() > 0) {

                    // check for invalid characters if it's not a comment
                    if (hasInvalidChar(unknownString)) {
                        String error = "Error: Invalid character in " + unknownString;
                        errorReporter.record(new ErrorMessage(error, new Position(tokenColumn, tokenLine)));
                    }
                    // check for \n or \r
                    else if (unknownString.contains("\n") || unknownString.contains("\r")) {
                        String error = "Error: EOL in string in " + unknownString;
                        errorReporter.record(new ErrorMessage(error, new Position(tokenColumn, tokenLine)));
                    } else {
                        token = new Token(unknownString, "newLine", new Position(tokenLine, tokenColumn));
                    }
                }
                tokenLine++;
                tokenColumn = 1;
                unknownString = "";
            }

            // if the cursor is not pointing towards an empty space, then continue building
            // the string
            else {
                unknownString += (char) i;
            }
        }

        catch (IOException e) {
            System.out.println("IO Exception");
        }
        return token;
    }

    // Method to read file character by character and send tokens to parser and
    // symbol table
    public IToken scan() {
        IToken token = null;
        try {
            while ((i = fis.read()) != -1) {
                token = getToken();
                if (token != null) {
                    // Insert mnemonic in the symbol table.
                    if (isMnemonic(token.getTokenString())) {
                        symbolTable.insertMnemonic(token.getTokenString(),
                                new Mnemonic(token.getTokenString(), getOpcode(token.getTokenString())));
                    }
                    return token; // Return token to the Parser
                }
            }
            this.finishScanning = true;

            fis.close(); // close the FileInputStream object
        }

        // FileInputStream exception handling, if file was not read
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        // Expection handling if error not related to the file
        catch (IOException e) {
            System.out.println("IO Exception");
            System.exit(0);
        }
        return token;

    }

    public boolean getFinishScanning() {
        return finishScanning;
    }

    private boolean isMnemonic(String mnemonicString) {
        if (Arrays.asList(inherentMnemonics).contains(mnemonicString)
                || Arrays.asList(immediateMnemonics).contains(mnemonicString)
                || Arrays.asList(relativeMnemonics).contains(mnemonicString)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasInvalidChar(String tokenString) {
        char[] chars = tokenString.toCharArray();
        int count = 0;
        for (char c : chars) {
            if (Character.isLetter(c) || Character.isDigit(c) || c == '\"' || c == '.' || c == '-') {
            } else {
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }

    }

    // returns opcode of mnemonics. In the case of immediate mnemonics, it returns
    // the base opcode
    private int getOpcode(String mnemonic) {

        // Inherent addressing
        if (mnemonic.equals("halt"))
            return 0;
        else if (mnemonic.equals("pop"))
            return 1;
        else if (mnemonic.equals("dup"))
            return 2;
        else if (mnemonic.equals("exit"))
            return 3;
        else if (mnemonic.equals("ret"))
            return 4;
        else if (mnemonic.equals("not"))
            return 12;
        else if (mnemonic.equals("and"))
            return 13;
        else if (mnemonic.equals("or"))
            return 14;
        else if (mnemonic.equals("xor"))
            return 15;
        else if (mnemonic.equals("neg"))
            return 16;
        else if (mnemonic.equals("inc"))
            return 17;
        else if (mnemonic.equals("dec"))
            return 18;
        else if (mnemonic.equals("add"))
            return 19;
        else if (mnemonic.equals("sub"))
            return 20;
        else if (mnemonic.equals("mul"))
            return 21;
        else if (mnemonic.equals("div"))
            return 22;
        else if (mnemonic.equals("rem"))
            return 23;
        else if (mnemonic.equals("shl"))
            return 24;
        else if (mnemonic.equals("shr"))
            return 25;
        else if (mnemonic.equals("teq"))
            return 26;
        else if (mnemonic.equals("tne"))
            return 27;
        else if (mnemonic.equals("tlt"))
            return 28;
        else if (mnemonic.equals("tgt"))
            return 29;
        else if (mnemonic.equals("tle"))
            return 30;
        else if (mnemonic.equals("tge"))
            return 31;
        // Immediate addressing
        else if (mnemonic.equals("enter.u5"))
            return 0x70;
        else if (mnemonic.equals("ldc.i3"))
            return 0x90;
        else if (mnemonic.equals("addv.u3"))
            return 0x98;
        else if (mnemonic.equals("ldv.u3"))
            return 0xA0;
        else if (mnemonic.equals("stv.u3"))
            return 0xA8;
        // Relative addressing
        else if (mnemonic.equals("br.i8"))
            return 0xE0;
        else if (mnemonic.equals("brf.i8"))
            return 0xE3;
        else if (mnemonic.equals("ldc.i8"))
            return 0xD9;
        else if (mnemonic.equals("ldv.u8"))
            return 0xB1;
        else if (mnemonic.equals("stv.u8"))
            return 0xB2;
        else if (mnemonic.equals("lda.i16"))
            return 0xD5;
        else
            return -1;
    }

    // private void insertInSymbolTable() {
    //     // insert all inherent mnemonics in symbol table
    //     for (String mnemonic : Arrays.asList(inherentMnemonics)) {
    //         symbolTable.insertMnemonic(mnemonic, new Mnemonic(mnemonic, getOpcode(mnemonic)));
    //     }

    //     // insert all immediate mnemonics in symbol table
    //     for (String mnemonic : Arrays.asList(immediateMnemonics)) {
    //         symbolTable.insertMnemonic(mnemonic, new Mnemonic(mnemonic, getOpcode(mnemonic)));
    //     }

    //     for (String mnemonic : Arrays.asList(relativeMnemonics)) {
    //         symbolTable.insertMnemonic(mnemonic, new Mnemonic(mnemonic, getOpcode(mnemonic)));

    //     }

    // }

    // public static void main(String[] args) {

    //     SymbolTable st = new SymbolTable();
    //     ErrorReporter er = new ErrorReporter();

    //     LexicalAnalyzer la = new LexicalAnalyzer("relaErrors.asm", st, er);

    //     while (la.getFinishScanning() == false) {
    //         IToken token = la.scan();
    //         if (token != null) {

    //             // String newLine = token.getEOL().equals("newLine") ? "newLine" : "not
    //             // newLine";

    //             // System.out.println(token.getTokenString() + " @column:" +
    //             // token.getPosition().getColumn() + " @line: "
    //             // + token.getPosition().getLine());
    //             // }
    //             // }


    //             // er.report();

    //         }
            

    //     }


    //     for (String s : st.getSymbolTable().keySet()) {
    //         System.out.println(s);
    //     }
    // }
}