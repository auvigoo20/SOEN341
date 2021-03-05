
# SOEN 341: Cm Cross-Assembler

#### Before merging your branch or pushing into `master`, please make sure to delete all `.class` files created after compiling java files in the command line

### **TODO for Sprint 2**  

- [x]  Create test case for CodeGenerator.java
- [x]  Create test case for LexicalAnalyzer.java
- [x]  Create test case for SymbolTable.java
- [x] Create driver file
- [x] Comment and format **all** code
- [x] Finalize UML Diagram
- [x] Write report


### **AUnit Testing**  

For sprint 2, we have 4 tests to run: `TestLexicalAnalyzer.java`, `TestParser.java`, `TestCGenerator.java` and `TestSymbolTable.java`.
We will combine all the outputs of each test in a single file called `Tests.txt` and then run **AUnit** with that file.

Follow the following commands to run the tests: 

```
javac -Xlint:unchecked aunit.java
javac TestCGenerator.java
javac TestLexicalAnalyzer.java 
javac TestParser.java 
javac TestSymbolTable.java 
java TestCGenerator > Tests.txt
java TestLexicalAnalyzer >> Tests.txt 
java TestParser >> Tests.txt 
java TestSymbolTable >> Tests.txt
java aunit Tests.txt
```

The final output should be:

```
....
OK
```

### Running the `Driver.java`
To run the code to generate the `.lst` file from the `.asm` file, you need to run it in command line since we want the user to input the `.asm` file name
without using any `Scanner` class. In order to do so, I added a constructor that will take in the value of `args[0]`, which is the first string that the user inputs in the command line when running `Driver.java`. In our case, `args[0] = "TestInherentMnemonics.asm"`

Follow these steps:

```
javac Driver.java
java Driver TestInherentMnemonics.asm
```
