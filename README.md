# React_Compiler_Antlrv4 💻
This project is a custom React compiler built using ANTLR v4


## Description
A compiler that recognizes React and Javascript code and outputs an AST and a symbol table with all the variables and functions declared in the provided code. 

## Project Stages
- Defining the Lexical Grammar for tokenizing the parsed input.
- Defining the Parser and writing all the rules for JavaScript, JSX, and React-specific syntax.
- Writing the code for the Visitor to generate an Abstract Syntax Tree (AST) from the parsed input.
- Generating the Symbol Table.

## How can I try it?
You write your React and Javascript code in 'Files/test.txt' file in the project folders.
and you run 'Main.java' to print the output (AST + Symbols Table)
