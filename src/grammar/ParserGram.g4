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
         | exportDefault
         ;

importStatement: IMPORT OPENBRACE? (ID | REACT_HOOKS)? CLOSEBRACE? FROM? StringLiteral  SEMICOLON ;
exportDefault: EXPORT DEFAULT ID SEMICOLON;
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


function: EXPORT* (functionDeclaration
                          | functionExpr
                          | arrowFunction
                          | anonymousFunction)
        ;
functionDeclaration: FUNCTION (ID)* OPENPAREN parameters CLOSEPAREN block;
callFunction: ID OPENPAREN parameters CLOSEPAREN SEMICOLON;
functionExpr: dataType ID EQUAL functionDeclaration SEMICOLON;

arrowFunction: (dataType ID EQUAL)? OPENPAREN parameters CLOSEPAREN EQUAL GT block SEMICOLON;

anonymousFunction:dataType ID EQUAL OPENPAREN functionDeclaration CLOSEPAREN OPENPAREN CLOSEPAREN SEMICOLON;

parameters : ID (COMMA ID)* | /* Empty parameters */;
block: OPENBRACE reacctDotHooks* statement* returnStatement* CLOSEBRACE;
returnStatement : RETURN (literal | jsxBlock ) SEMICOLON;
jsxBlock: OPENPAREN jsx_element CLOSEPAREN;

createElement: CREATE_ELEMENT OPENPAREN ( HTML_TAGS_ELEMENT | callFunction | ID) COMMA ( NULL  | props  )? COMMA children? CLOSEPAREN ;

props: OPENBRACE prop (COMMA prop)* CLOSEBRACE;

prop: JSX_CLASS COLON literal;
children: (OPENBRACE createElement (COMMA createElement)* CLOSEBRACE | StringLiteral);
/*createElement*/

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

reacctDotHooks: REACT DOT (reactHooks | createElement)  SEMICOLON;
reactHooks: REACT_HOOKS OPENPAREN (INTEGER | parameters | arrowFunction) CLOSEPAREN;



jsx_element:jsx_open_tag  (jsx_element|jsx_openSelf_close|jsx_Expreeion|element_js|jsx_text) * CLOSE_TAG;

jsx_open_tag:OPEN_TAG attribute* jsx_class* attribute_click* GT;

jsx_class:JSX_CLASS EQUAL StringLiteral ;

attribute:ID EQUAL StringLiteral;

jsx_openSelf_close:OPEN_TAG_SELF attribute* ID? DIVIDE GT;


attribute_click:ON_CLICK EQUAL jsx_Expreeion ;

jsx_Expreeion:OPENBRACE .*? CLOSEBRACE ;

element_js:LT .*? DIVIDE GT;

jsx_text: ~('<'|'>'|'{'|'}' )+;