parser grammar ParserGram;

options {tokenVocab = LexerGram;}

program:  statement* EOF;


// Statement can be a variable declaration, an assignment, or a string declaration
statement: varDeclaration | assignment ;

// Variable declaration rule
varDeclaration: VAR ID ASSIGN expression SEMICOLON;

// Assignment rule
assignment: ID ASSIGN expression SEMICOLON;


// Expression can be a numeric literal or an identifier
expression: INTEGER | FLOAT |  StringLiteral;








