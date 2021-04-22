
# SOEN 341: Cm Cross-Assembler

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
javac [fileToTest.java]
java [fileToTest] > Tests.txt
java aunit Tests.txt
```

The output should be:

```
.
OK
```

### Running the `cma.java`

```
Usage: java cma [ Options ] <file>.asm

where options are:

Short version  Long version    Meaning
-h             -help           Print the usage of the program.
-v             -verbose        Verbose during the execution of the program.
-b             -banner         Print the banner of the program.
-l             -listing        Generate a listing of the assembly file.
```
