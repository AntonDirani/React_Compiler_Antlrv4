parser grammar ParserGram;

options {tokenVocab = LexerGram;}

program:  statement* EOF;


//Statement can be a variable declaration, an assignment, or a string declaration
statement: varDeclaration | assignment ;

//Variable declaration rule
varDeclaration: VAR ID EQUAL expression SEMICOLON;

// Assignment rule
assignment: ID EQUAL expression SEMICOLON;


// Expression can be a numeric literal or an identifier
expression: INTEGER | FLOAT |  StringLiteral | BOOL_TRUE_FALSE | ;

forLoop: FOR OPENPAREN (varDeclaration | assignment ) SEMICOLON;








