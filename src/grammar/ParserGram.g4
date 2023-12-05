parser grammar ParserGram;

options {tokenVocab = LexerGram;}

program: classDeclaration |statement* EOF;


//Statement can be a variable declaration, an assignment, or a string declaration
statement: importStatement
         | variableDeclaration
         | assignment
         | function
         | callStatement
         | ifStatement
         | forStatement
         | printOrLogStatement
         | whileStatement
         | counterStatement
         | ifElseStatement
         | ifElseIfStatement
         | returnStatement
         | createAnObjectStatement
         | stringInterpolationStatement
         ;

importStatement: IMPORT OPENBRACE? (ID | REACT_HOOKS)? CLOSEBRACE? FROM? StringLiteral  SEMICOLON ;

//Variable declaration rule
variableDeclaration: dataType ID EQUAL literal SEMICOLON | letDecleration | varDeclaration;
dataType: (VAR | LET | CONST);
letDecleration: LET ID (EQUAL literal) | LET ID SEMICOLON;
varDeclaration: VAR ID (EQUAL literal) | VAR ID SEMICOLON;

// Assignment rule
assignment: ID EQUAL literal SEMICOLON;

// Expression can be a numeric literal or an identifier
literal: INTEGER | FLOAT |  StringLiteral | BOOL_TRUE_FALSE | NULL | reactHooks ;

//forLoop: FOR OPENPAREN (varDeclaration | assignment ) SEMICOLON;

///for statement
forStatement: FOR OPENPAREN letDecleration SEMICOLON comparisonExpr SEMICOLON counterStatement CLOSEPAREN forBodyStatement;
expr: ID | INTEGER | FLOAT ;
comparisonExpr: expr LT expr
              | expr GT expr
              | expr LTE expr
              | expr GTE expr;

forBodyStatement: OPENBRACE (forStatement | printOrLogStatement | ifStatement) CLOSEBRACE;

printOrLogStatement: CONSOLE DOT LOG OPENPAREN (expr | StringLiteral | accessMethodInLogStatement) CLOSEPAREN SEMICOLON;

whileStatement: WHILE OPENPAREN (comparisonExpr | BOOL_TRUE_FALSE ) CLOSEPAREN OPENBRACE (statement |counterStatement SEMICOLON)+ CLOSEBRACE;

counterStatement: expr((PLUS expr| MINUS expr| MULTIPLY expr | DIVIDE expr | PLUSPLUS | MINUSMINUS) |expr)+;

logicalExpr: AND | OR | EQUALEQUAL | NOTEQUAL;

ifStatement: IF OPENPAREN((comparisonExpr | (comparisonExpr logicalExpr)* comparisonExpr) | BOOL_TRUE_FALSE )CLOSEPAREN OPENBRACE statement CLOSEBRACE;

ifElseStatement:ifStatement elseStatemetn;// ELSE OPENBRACE statement CLOSEBRACE;

elseStatemetn:ELSE OPENBRACE statement CLOSEBRACE;

ifElseIfStatement: ifStatement (ELSE ifStatement)+ elseStatemetn;

 callStatement: callMethod | callFunction;
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

function: EXPORT* (functionDeclaration
                          | functionExpr
                          | arrowFunction
                          | anonymousFunction)
        ;
functionDeclaration: FUNCTION (ID)* OPENPAREN parameters CLOSEPAREN block;
callFunction: ID OPENPAREN parameters CLOSEPAREN SEMICOLON;
functionExpr: dataType ID EQUAL functionDeclaration SEMICOLON;
/*const myFunction = function() {

  return ;
};*/
arrowFunction: (dataType ID EQUAL)? OPENPAREN parameters CLOSEPAREN EQUAL GT block SEMICOLON;
/*const myFunction = () => {

};*/
anonymousFunction:dataType ID EQUAL OPENPAREN functionDeclaration CLOSEPAREN OPENPAREN CLOSEPAREN SEMICOLON;
/*const result = (function() {})();
*/
parameters : ID (COMMA ID)* | /* Empty parameters */;
block: OPENBRACE reacctDotHooks* statement* returnStatement* CLOSEBRACE;
returnStatement : RETURN (literal | jsxBlock ) SEMICOLON;
jsxBlock: OPENPAREN jsx_element CLOSEPAREN;



classDeclaration: class+ | class inheritsClass*;

class : CLASS ID OPENBRACE bodyOfClass CLOSEBRACE ;
inheritsClass : CLASS ID  EXTENDS ID OPENBRACE bodyOfClass CLOSEBRACE ;
bodyOfClass:  constructorMethod methodDeclaration* | statement*;

methodDeclaration : method | staticMethod;
method: ID OPENPAREN  parameters CLOSEPAREN  OPENBRACE bodyOfMethod CLOSEBRACE ;
bodyOfMethod: statement*;

staticMethod : STATIC method;

callMethod: ID DOT callFunction;
accessMethodInLogStatement: ID OPENPAREN parameters CLOSEPAREN  | ID DOT ID OPENPAREN parameters CLOSEPAREN;
//callStaticMethod: ID DOT callMethod;

constructorMethod:  CONSTRUCTOR OPENPAREN parameters CLOSEPAREN OPENBRACE bodyOfConstructor CLOSEBRACE;
bodyOfConstructor: (THIS DOT ID EQUAL ID SEMICOLON)*;
createAnObjectStatement: dataType ID EQUAL NEW ID OPENPAREN (literal (COMMA literal)*) CLOSEPAREN SEMICOLON;  ///const myAnimal = new Animal("333",3);

stringInterpolationStatement: SDOLLAR OPENBRACE THIS DOT ID CLOSEBRACE; //${}

/*
class Animal {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  getInfo() {
   return `${this.name} is ${this.age} years old.`;
  }

  static greet() {
    console.log("Hello from the Animal class!");
  }
}
*/

//const myAnimal = new Animal("Buddy", 3);

//console.log(myAnimal.getInfo());

//Animal.greet();

reacctDotHooks: REACT DOT reactHooks SEMICOLON;
reactHooks: REACT_HOOKS OPENPAREN (INTEGER | parameters | arrowFunction) CLOSEPAREN;



jsx_element:jsx_open_tag (jsx_element|jsx_openSelf_close|jsx_text) * CLOSE_TAG;
jsx_open_tag: OPEN_TAG  GT;
attribute: ATTRIBUTES_JSX '=' StringLiteral;
jsx_openSelf_close:OPEN_TAG_SELF attribute* '/' GT;
jsx_open_clos:OPEN_TAG_ATT attribute*;

jsx_text: ~'<'+;