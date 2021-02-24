
# SOEN 341: Cm Cross-Assembler

#### Before merging your branch or pushing into `master`, please make sure to delete all `.class` files created after compiling java files in the command line

### **TODO for Sprint 2**  

- [x]  Create test case for CodeGenerator.java
- [x]  Create test case for LexicalAnalyzer.java
- [x]  Create test case for SymbolTable.java
- [ ] Create driver file
- [ ] Comment and format **all** code
- [ ] Finalize UML Diagram
- [ ] Write report


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
