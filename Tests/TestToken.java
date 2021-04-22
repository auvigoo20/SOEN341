package Tests;

import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;

public class TestToken {
    public static void main(String[] args) {

        IToken t1 = new Token("halt", "", new Position(1,1));

        System.out.println("Test Token");

        // Expected Output
        System.out.println("halt 1 1");

        // Actual output
        System.out.println(t1.getTokenString() + " " + t1.getPosition().getColumn() +" "+ t1.getPosition().getLine());
    }

}
