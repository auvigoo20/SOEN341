package SourceFiles;
import InterfaceFiles.*;

public class Position implements IPosition{
    
    private int column;
    private int line;
    
    public Position(){

    }

    public Position(int column, int line){
        this.column=column;
        this.line=line;
    }
    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return this.line;
    }

    public void setLine(int line) {
        this.line = line;
    }

  

    




    
}
