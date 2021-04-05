package SourceFiles;

import InterfaceFiles.*;

public class Position implements IPosition {

    // Attributes
    private int column;
    private int line;

    // Default constructor
    public Position() {

    }

    // parametrized constructor
    public Position(int column, int line) {
        this.column = column;
        this.line = line;
    }

    // get the column of the token
    public int getColumn() {
        return this.column;
    }

    // get the line of the token
    public int getLine() {
        return this.line;
    }

}
