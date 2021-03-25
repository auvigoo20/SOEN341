package SourceFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import InterfaceFiles.*;

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
    private boolean finishScanning;

    // Creating a constructor for the lexicalAnalyzer, the default constructor
    public LexicalAnalyzer() {

        // Opening the input file specified
        try {
            this.fis = new FileInputStream("TestInherentMnemonics.asm");
            this.finishScanning = false;
        }

        // Checking if the file was read correctly.
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    // Creating a constructor for the lexicalAnalyzer, the parametrized constructor
    //NOTES FROM PROF: should have a mnemonic class that has all characteristics of the mnemonic (name, size, opcode, type, etc.)
    public LexicalAnalyzer(String fileName, ISymbolTable symbolTable, IErrorReporter errorReporter) {

        // Checking if the file was read correctly.
        try {
            this.fis = new FileInputStream(fileName);
            this.symbolTable = symbolTable; // constructor injection of symbol table. NOTES FROM PROF: should insert mnemonic objects in the constructor
            this.finishScanning = false;
            this.errorReporter = errorReporter; // constructor injection of error reporter
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    // Method to sequentially generate tokens while reading the file
    public IToken getToken() {

        IToken token = null;

        try {
            // if the token is a comment (starts with a ;), build the string until reaching
            // end of line
            if (i == 59) {
                unknownString += (char) i;
                while ((i = fis.read()) != 10) {
                    unknownString += (char) i;
                }
                unknownString = unknownString.trim();
                token = new Token(unknownString, "newLine", new Position(tokenColumn, tokenLine));
                tokenLine++;
                tokenColumn = 1;
                unknownString = "";
            }

            // if the token does not contain the EOL marker
            else if ((i == 32) && unknownString.trim().length() > 0) {

                unknownString = unknownString.trim();
                // check for invalid characters if it's not a comment
                if (!unknownString.matches("[a-zA-Z0-9\".-]*")) {
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
                    if (!unknownString.matches("[a-zA-Z0-9\".-]*")) {
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
                    // Insert mnemonic in the symbol table. This mnemonic is the same as the one
                    // that is sent as a token.
                    if (Arrays.asList(inherentMnemonics).contains(token.getTokenString())
                            || Arrays.asList(immediateMnemonics).contains(token.getTokenString())) {
                        symbolTable.insertMnemonic(token.getTokenString(),
                                new Instruction(token.getTokenString(), token.getPosition()));
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
            System.out.println("Error");
            System.exit(0);
        }
        return token;

    }

    public ISymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public void setSymbolTable(ISymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public boolean getFinishScanning() {

        return finishScanning;
    }

    public void setFinishScanning(boolean fs) {
        finishScanning = fs;
    }

}