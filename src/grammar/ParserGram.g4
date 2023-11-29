parser grammar ParserGram;

options {tokenVocab = LexerGram;}

program:  statement* EOF;


//Statement can be a variable declaration, an assignment, or a string declaration
statement: varDeclaration | assignment | functionDeclaration ;

//Variable declaration rule
varDeclaration: VAR ID EQUAL literal SEMICOLON;

// Assignment rule
assignment: ID EQUAL literal SEMICOLON;


// Expression can be a numeric literal or an identifier
literal: INTEGER | FLOAT |  StringLiteral | BOOL_TRUE_FALSE | ;

forLoop: FOR OPENPAREN (varDeclaration | assignment ) SEMICOLON;

functionDeclaration: FUNCTION ID OPENPAREN parameters CLOSEPAREN block;

parameters : ID (COMMA ID)* | /* Empty parameters */;

block: OPENBRACE statement* returnStatement CLOSEBRACE;

returnStatement : RETURN literal SEMICOLON;








