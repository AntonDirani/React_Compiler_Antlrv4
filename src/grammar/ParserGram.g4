parser grammar ParserGram;

options {tokenVocab = LexerGram;}

program:  statement* EOF;


//Statement can be a variable declaration, an assignment, or a string declaration
statement: variableDeclaration
         | assignment
         | function
         | ifStatement
         |forStatement
         |printStatement
         |whileStatement
         |counterStatement
         |ifElseStatement
         |ifElseIfStatement;

//Variable declaration rule
variableDeclaration: dataType ID EQUAL literal SEMICOLON | letDecleration | varDeclaration;
dataType: (VAR | LET | CONST);
letDecleration: LET ID (EQUAL literal) | LET ID SEMICOLON;
varDeclaration: VAR ID (EQUAL literal) | VAR ID SEMICOLON;

// Assignment rule
assignment: ID EQUAL literal SEMICOLON;

// Expression can be a numeric literal or an identifier
literal: INTEGER | FLOAT |  StringLiteral | BOOL_TRUE_FALSE | NULL;

//forLoop: FOR OPENPAREN (varDeclaration | assignment ) SEMICOLON;

///for statement
forStatement: FOR OPENPAREN letDecleration SEMICOLON comparisonExpr SEMICOLON counterStatement CLOSEPAREN forBodyStatement;

expr: ID | INTEGER | FLOAT ;


comparisonExpr: expr LT expr
                  | expr GT expr
                  | expr LTE expr
                  | expr GTE expr;
forBodyStatement: OPENBRACE (forStatement | printStatement | ifStatement) CLOSEBRACE;
printStatement: CONSOLE DOT LOG OPENPAREN (expr | StringLiteral) CLOSEPAREN SEMICOLON;

whileStatement: WHILE OPENPAREN (comparisonExpr | BOOL_TRUE_FALSE ) CLOSEPAREN OPENBRACE (statement |counterStatement SEMICOLON)+ CLOSEBRACE;
counterStatement: expr((PLUS expr| MINUS expr| MULTIPLY expr | DIVIDE expr | PLUSPLUS | MINUSMINUS) |expr)+;

logicalExpr: AND | OR | EQUALEQUAL | NOTEQUAL;
ifStatement: IF OPENPAREN((comparisonExpr | (comparisonExpr logicalExpr)* comparisonExpr) | BOOL_TRUE_FALSE )CLOSEPAREN OPENBRACE statement CLOSEBRACE;

ifElseStatement:ifStatement elseStatemetn;// ELSE OPENBRACE statement CLOSEBRACE;
elseStatemetn:ELSE OPENBRACE statement CLOSEBRACE;

ifElseIfStatement: ifStatement (ELSE ifStatement)+ elseStatemetn;

/*while (i <= 5)
  {
    for(let i =0; i< 5;i++)
    {
     console.log(i);
     }
    i++;
  }*/

/*
if (true) {
  console.log("You are a minor.");
} else if (age >= 18 && age <= 24 && age <= 2)
{
  console.log("You are a student.");
}  else if (age >= 25 && age <= 60) {
  console.log("You are an adult.");
} else {
  console.log("You are a senior citizen.");
}*/

function: functionDeclaration
        | functionExpr
        | arrowFunction
        | anonymousFunction
        ;
functionDeclaration: FUNCTION (ID)* OPENPAREN parameters CLOSEPAREN block;
functionExpr: dataType ID EQUAL functionDeclaration SEMICOLON;
/*const myFunction = function() {

  return ;
};*/
arrowFunction: dataType ID EQUAL OPENPAREN parameters CLOSEPAREN EQUAL GT block SEMICOLON;
/*const myFunction = () => {

};*/
anonymousFunction:dataType ID EQUAL OPENPAREN functionDeclaration CLOSEPAREN OPENPAREN CLOSEPAREN SEMICOLON;

/*const result = (function() {})();
*/
parameters : ID (COMMA ID)* | /* Empty parameters */;

block: OPENBRACE statement* returnStatement* CLOSEBRACE;

returnStatement : RETURN literal SEMICOLON;
