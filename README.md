
# SOEN 341: Cm Cross-Assembler

#### Before merging your branch or pushing into `master`, please make sure to delete all `.class` files created after compiling java files in the command line

## Key Concept Model
<p align="center">
<img src="https://github.com/auvigoo20/SOEN341/blob/master/diagrams/KeyConcept.png" />
</p>

## Overall Architecture of the Cross-Assembler

_Cross-Assembler Package Architecture:_

<p align="center">
<img src="https://github.com/auvigoo20/SOEN341/blob/master/diagrams/CrossAssembler_package.png" />
</p>

_Core Package Architecture:_

<p align="center">
<img src="https://github.com/auvigoo20/SOEN341/blob/master/diagrams/Core_package.png" />
</p>

_Frontend Package Architecture:_

<p align="center">
<img src="https://github.com/auvigoo20/SOEN341/blob/master/diagrams/Frontend_package.png" />
</p>

_Backend Package Architecture:_

<p align="center">
<img src="https://github.com/auvigoo20/SOEN341/blob/master/diagrams/Backend_package.png" />
</p>


### **AUnit Testing**  


Follow the following commands to run the tests: 

```
javac -Xlint:unchecked aunit.java
javac TestCGenerator.java
javac TestComment.java
javac TestErrorMessage.java
javac TestErrorReporter.java
javac TestInstruction.java
javac TestLexicalAnalyzer.java
javac TestLineStatement.java
javac TestParser.java
javac TestPosition.java
javac TestSymbolTable.java
javac TestToken.java
java TestCGenerator > Tests.txt
java TestComment >> Tests.txt
java TestErrorMessage >> Tests.txt
java TestErrorReporter >> Tests.txt
java TestInstruction >> Tests.txt
java TestLexicalAnalyzer >> Tests.txt
java TestLineStatement >> Tests.txt
java TestParser >> Tests.txt
java TestPosition >> Tests.txt
java TestSymbolTable >> Tests.txt
java TestToken >> Tests.txt
java aunit Tests.txt
```

The final output should be:

```
...........
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
