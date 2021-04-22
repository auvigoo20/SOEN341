
# SOEN 341: Cm Cross-Assembler

## Project Purpose

This project uses the Java programming language to create a cross-assembler that can process the Cm assembly language (a fictional language created by Dr. Michel de Champlain). The code reads an assembly language source code file and create an in-memory intermediate representation (IR) of the assembly language program. It then traverses this IR and writes the executable file.

The front-end consists of a lexical analyzer and parser, which translate the source code into an in-memory intermediate representation (IR) built from a sequence of instructions. Two additional helpers are a symbol table (for all opcodes and labels) and an error reporter.

The back-end consists of a code generator that traverses the IR and generates assembly source code (.asm) listing and/or binary (virtual) machine code.

The functionality of the cross-assembler is represented in the Key Concept Model.

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

In order to perform unit and integration tests, `AUnit` (a testing software developed by Dr. Michel de Champlain) was used.


Follow the following commands to run the tests: 

```
javac -Xlint:unchecked aunit.java
javac [fileToTest.java]
java [fileToTest] > Tests.txt
java aunit Tests.txt
```

The output should be:

```
.
OK
```

### Running the cross-assembler

The `cma.java` file contains the main method to run the cross-assembler. The input assembly file should be written in `Cm`. The following shows the possible options and format to run the cross-assembler in the command-line:

_*Note: make sure to compile `cma.java` (`javac cma.java`) before running the following commands._

```
Usage: java cma [ Options ] <file>.asm

where options are:

Short version  Long version    Meaning
-h             -help           Print the usage of the program.
-v             -verbose        Verbose during the execution of the program.
-b             -banner         Print the banner of the program.
-l             -listing        Generate a listing of the assembly file.
```

An example of running `TestImmediate.asm` to generate the verbose, listing and executable file:

`java cma -v -l TestImmediate.asm`
