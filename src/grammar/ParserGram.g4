parser grammar ParserGram;

options {tokenVocab = LexerGram;}

program:statement* EOF;


//Statement can be a variable declaration, an assignment, or a string declaration
statement: classDeclaration
         | importStatement
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
         | createAnObjectStatement
         | stringInterpolationStatement
         | exportDefault
         | returnStatement //consoleLog
         | useState
         ;


//accessModifiers: PUBLIC|PRIVATE|PROTECTED;

importStatement: IMPORT OPENBRACE? (ID | hook | REACT)? CLOSEBRACE? FROM? StringLiteral  SEMICOLON? ;
exportDefault: EXPORT DEFAULT ID SEMICOLON;

//Variable declaration rule
variableDeclaration: dataType ID (EQUAL variableValues )? SEMICOLON | letDecleration | varDeclaration;
dataType: (VAR | LET | CONST );
//variableValues:(literal | hook | ID (DOT ID)?);
variableValues:(literal | hook |expr| ID (DOT ID)?);

letDecleration: LET ID (EQUAL literal)? SEMICOLON;

varDeclaration: VAR ID (EQUAL literal)? SEMICOLON;

//consoleLog: CONSOLE_LOG OPENPAREN StringLiteral CLOSEPAREN SEMICOLON;

// Assignment rule
assignment: ID EQUAL variableValues SEMICOLON;

// Expression can be a numeric literal or an identifier
literal: INTEGER #integerLiteral
       | FLOAT   #floatLiteral
       | StringLiteral #stringLiteral
       | BOOL    #boolLiteral
       | NULL    #null
       | useCallback #uCallback
       | useContext  #uContext
       | useRef      #uRef
       ;

//forLoop: FOR OPENPAREN (varDeclaration | assignment ) SEMICOLON;


///for statement
forStatement: FOR OPENPAREN letDecleration SEMICOLON comparisonExpr SEMICOLON counterStatement CLOSEPAREN forBodyStatement;

expr: ID      #idExpr
    | INTEGER #intExpr
    | FLOAT   #floatExpr
     ;

comparisonExpr: expr LT expr
              | expr GT expr
              | expr LTE expr
              | expr GTE expr;

forBodyStatement: OPENBRACE (forStatement | printOrLogStatement | ifStatement) CLOSEBRACE;

printOrLogStatement: CONSOLE DOT LOG OPENPAREN (expr | literal | accessMethodInLogStatement)? CLOSEPAREN SEMICOLON;

whileStatement: WHILE OPENPAREN (comparisonExpr | BOOL_TRUE_FALSE ) CLOSEPAREN OPENBRACE (statement |counterStatement SEMICOLON)+ CLOSEBRACE;

counterStatement: expr((PLUS expr| MINUS expr| MULTIPLY expr | DIVIDE expr | PLUSPLUS | MINUSMINUS) |expr)+;

logicalExpr: AND
           | OR
           | EQUALEQUAL
           | NOTEQUAL
           ;

ifStatement: IF OPENPAREN((comparisonExpr | (comparisonExpr logicalExpr)* comparisonExpr) | BOOL_TRUE_FALSE )CLOSEPAREN OPENBRACE statement CLOSEBRACE;

ifElseStatement:ifStatement elseStatemetn;// ELSE OPENBRACE statement CLOSEBRACE;

elseStatemetn:ELSE OPENBRACE statement CLOSEBRACE;

ifElseIfStatement: ifStatement (ELSE ifStatement)+ elseStatemetn;

 callStatement: callMethod | callFunction;


////function

function: EXPORT* ( functionDeclaration
                  | arrowFunction
                  | anonymousFunction)
                  ;
functionDeclaration: FUNCTION (ID)? OPENPAREN parameters CLOSEPAREN block;

callFunction: ID OPENPAREN parameters CLOSEPAREN SEMICOLON;

anonymousFunction: dataType ID EQUAL functionDeclaration SEMICOLON;


////type of function

arrowFunction: (dataType ID EQUAL)? OPENPAREN parameters CLOSEPAREN EQUAL GT block SEMICOLON?;

//anonymousFunction:dataType ID EQUAL OPENPAREN functionDeclaration CLOSEPAREN OPENPAREN CLOSEPAREN SEMICOLON;

parameters : OPENBRACE? ID (COMMA ID)* OPENBRACE? | /* Empty parameters */;

block: OPENBRACE (reacctDotHooks| hook |returnStatement| printOrLogStatement)? CLOSEBRACE;

returnStatement : RETURN (ID | literal | jsxBlock | arrowFunction |  reactDotCreateElement)? SEMICOLON;

////class

classDeclaration: class+ | class inheritsClass*;

class : CLASS ID OPENBRACE bodyOfClass CLOSEBRACE ;

inheritsClass : CLASS ID  EXTENDS ID OPENBRACE bodyOfClass CLOSEBRACE ;

bodyOfClass:  constructorMethod methodDeclaration* | statement*;
////method in class
methodDeclaration : method | staticMethod;

method: ID OPENPAREN  parameters CLOSEPAREN  OPENBRACE bodyOfMethod CLOSEBRACE ;

bodyOfMethod: statement*;//////////////////////////////////////////////////

////type of method
staticMethod : STATIC method;

constructorMethod:  CONSTRUCTOR OPENPAREN parameters CLOSEPAREN OPENBRACE bodyOfConstructor CLOSEBRACE;

bodyOfConstructor: (THIS DOT ID EQUAL ID SEMICOLON)*;

callMethod: ID DOT callFunction;

accessMethodInLogStatement: ID OPENPAREN parameters CLOSEPAREN  | ID DOT ID OPENPAREN parameters CLOSEPAREN;

createAnObjectStatement: dataType ID EQUAL NEW ID OPENPAREN (literal (COMMA literal)*) CLOSEPAREN SEMICOLON;  ///const myAnimal = new Animal("333",3);

stringInterpolationStatement: SDOLLAR OPENBRACE THIS DOT ID CLOSEBRACE; //${}

////react
reactDotCreateElement: REACT DOT createElement;
createElement: CREATE_ELEMENT OPENPAREN ( WS* HTML_TAGS_ELEMENT  | callFunction | ID) COMMA ( NULL  | props  )? (COMMA children)? CLOSEPAREN ;

props: OPENBRACE+ prop (COMMA prop)* CLOSEBRACE+;

prop: (JSX_CLASS | ON_CLICK | SRC | ALT | DISPLAY | FLEX_FLOW | JUSTIFY | ALIGN_CONTENT ) COLON (literal | ID);
children: (OPENBRACE? (REACT DOT)? createElement (COMMA (REACT DOT)? createElement)* COMMA? CLOSEBRACE? | StringLiteral  );
/*createElement*/


reacctDotHooks: REACT DOT hook  SEMICOLON;
//reactHooks: REACT_HOOKS OPENPAREN (INTEGER | parameters | arrowFunction) CLOSEPAREN;
//hook: USE_STATE | USE_EFFECT | USE_CALLBACK 3 | USE_CONTEXT | USE_REF;

hook: useState | useEffect | useCallback | useContext | useRef | USE_STATE;
useState: CONST? pairValue? USE_STATE OPENPAREN (INTEGER | parameters | arrowFunction)? CLOSEPAREN SEMICOLON? ;
pairValue:OPENBRACKET ID COMMA ID CLOSEBRACKET EQUAL;

useEffect: USE_EFFECT OPENPAREN arrowFunction? block? CLOSEPAREN ;
clickHandler: CONST CLICK_HANDLER EQUAL arrowFunction;
useCallback: USE_CALLBACK OPENPAREN parameters COMMA block CLOSEPAREN ;

useContext:  USE_CONTEXT OPENPAREN CLOSEPAREN ;

useRef: USE_REF OPENPAREN INTEGER? CLOSEPAREN ;

////jsx

jsxBlock: OPENPAREN jsx_element CLOSEPAREN;

jsx_element:jsx_open_tag  (jsx_element|jsx_openSelf_close|jsx_Expreeion|element_js|jsx_text) * CLOSE_TAG;

jsx_open_tag:OPEN_TAG attribute* jsx_class* attribute_click* GT;

jsx_class: (JSX_CLASS | STYLE) EQUAL (StringLiteral | props ) ;

attribute:ID EQUAL StringLiteral;

jsx_openSelf_close:OPEN_TAG_SELF attribute* ID? DIVIDE GT;


attribute_click:ON_CLICK EQUAL jsx_Expreeion ;

jsx_Expreeion:OPENBRACE .*? CLOSEBRACE ;

element_js:LT .*? DIVIDE GT;

jsx_text: ~('<'|'>'|'{'|'}' | ')' | '(' )+;