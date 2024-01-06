parser grammar ParserGram;

options {tokenVocab = LexerGram;}

program:statement* EOF;

////js
statement:classDeclaration
         |inheritsClassDeclaration
         | importStatement
         | variableDeclaration
         | assignment
         | function
         | callStatement
         | ifStatement
         | forStatement
         | printOrLogStatement
         | whileStatement
         | ifElseStatement
         | ifElseIfStatement
         | multiIfElseStatement
         | createAnObjectStatement
         | stringInterpolationStatement
         | exportDefault
         | returnStatement
         | jsxBlock
         ;


importStatement: IMPORT OPENBRACE? (ID | REACT)? CLOSEBRACE? FROM? StringLiteral  SEMICOLON? ;

exportDefault: EXPORT DEFAULT ID SEMICOLON?;


variableDeclaration: dataType ID (EQUAL variableValues )? SEMICOLON? | letDecleration | varDeclaration;

dataType: (VAR | LET | CONST );

variableValues:(literal | hook |expr| ID (DOT ID)? );

letDecleration: LET ID (EQUAL literal)? SEMICOLON;

varDeclaration: VAR ID (EQUAL literal)? SEMICOLON;



assignment: ID EQUAL variableValues SEMICOLON;

literal: INTEGER #integerLiteral
       | FLOAT   #floatLiteral
       | StringLiteral #stringLiteral
       | BOOL    #boolLiteral
       | NULL    #null
       | useCallback #uCallback
       | useContext  #uContext
       | useRef      #uRef
       ;

expr: ID      #idExpr
    | INTEGER #intExpr
    | FLOAT   #floatExpr
    | BOOL   #boolExpr
     ;

forStatement: FOR OPENPAREN variableDeclaration comparisonExpr SEMICOLON exprOperation CLOSEPAREN forBodyStatement;

forBodyStatement: OPENBRACE (forStatement | printOrLogStatement | ifStatement) CLOSEBRACE;

comparisonExpr: expr LT expr #lessThan
              | expr GT expr #greaterThan
              | expr LTE expr #lessThanOrEqual
              | expr GTE expr #greaterThanOrEqual
              ;

printOrLogStatement: CONSOLE DOT LOG OPENPAREN (expr | literal | accessMethodInLogStatement)? CLOSEPAREN SEMICOLON;

whileStatement: WHILE OPENPAREN (comparisonExpr | expr ) CLOSEPAREN OPENBRACE statement exprOperation SEMICOLON CLOSEBRACE;

exprOperation:    expr PLUS expr     #add
                | expr MINUS expr    #min
                | expr MULTIPLY expr #mul
                | expr DIVIDE expr   #div
                | expr PLUSPLUS      #increment
                | expr MINUSMINUS    #decrement
                ;

logicalExpr: comparisonExpr AND comparisonExpr  #and
           | comparisonExpr OR comparisonExpr #or
           | comparisonExpr EQUALEQUAL comparisonExpr #equalEqual
           | comparisonExpr NOTEQUAL comparisonExpr  #notEqual
           ;

ifStatement: IF OPENPAREN(comparisonExpr | logicalExpr | expr ) CLOSEPAREN OPENBRACE statement CLOSEBRACE;

ifElseStatement:ifStatement elseStatement;

elseStatement:ELSE OPENBRACE statement CLOSEBRACE;

multiIfElseStatement: ifElseIfStatement elseStatement;
ifElseIfStatement: ifStatement (ELSE ifStatement)+ ;



function: EXPORT* ( functionDeclaration
                  | arrowFunction
                  | anonymousFunction)
                  ;
functionDeclaration: FUNCTION (ID)? OPENPAREN parameters CLOSEPAREN block;

anonymousFunction: dataType ID EQUAL functionDeclaration SEMICOLON;

arrowFunction: (dataType ID EQUAL)? OPENPAREN parameters CLOSEPAREN EQUAL GT block SEMICOLON?;

parameters : OPENBRACE? ID (COMMA ID)* OPENBRACE? | /* Empty parameters */;

block: OPENBRACE (variableDeclaration | reacctDotHooks| hook |returnStatement| printOrLogStatement| arrowFunction)+ CLOSEBRACE;

returnStatement : RETURN (ID | literal | jsxBlock | arrowFunction |  reactDotCreateElement)? SEMICOLON;


callStatement: callMethod | callFunction;

callFunction: bodyOfCall SEMICOLON;

bodyOfCall:ID OPENPAREN parameters CLOSEPAREN;



classDeclaration : CLASS ID OPENBRACE bodyOfClass? CLOSEBRACE ;
inheritsClassDeclaration : CLASS idExtendsId OPENBRACE bodyOfClass? CLOSEBRACE ;
idExtendsId:ID EXTENDS ID;

bodyOfClass:  methodDeclaration*;

methodDeclaration : constructorMethod | method | staticMethod;

method: ID OPENPAREN  parameters CLOSEPAREN  OPENBRACE bodyOfMethod CLOSEBRACE ;

bodyOfMethod: statement*;

staticMethod : STATIC method;

constructorMethod:  CONSTRUCTOR OPENPAREN parameters CLOSEPAREN OPENBRACE bodyOfConstructor CLOSEBRACE;

bodyOfConstructor: (THIS DOT ID EQUAL ID SEMICOLON)*;

callMethod: ID DOT bodyOfCall SEMICOLON;

accessMethodInLogStatement: bodyOfCall | ID DOT bodyOfCall;


createAnObjectStatement: dataType ID EQUAL bodyOfObject SEMICOLON;
bodyOfObject: NEW ID OPENPAREN literalOrMore CLOSEPAREN;
literalOrMore: literal (COMMA literal)* ;

stringInterpolationStatement: SDOLLAR OPENBRACE THIS DOT ID CLOSEBRACE; //${}

////react
reactDotCreateElement: REACT DOT createElement;

createElement: CREATE_ELEMENT OPENPAREN type COMMA createElementProps? (COMMA children)? CLOSEPAREN ;

type:(  StringLiteral  | callFunction | ID);
createElementProps:( NULL  | props | OPENBRACE CLOSEBRACE );
props: OPENBRACE+ prop (COMMA prop)* CLOSEBRACE+;

prop: (ID | JSX_CLASS | ON_CLICK) COLON (StringLiteral | ID);
children: (OPENBRACE? (REACT DOT)? createElement (COMMA (REACT DOT)? createElement)* COMMA? CLOSEBRACE? | StringLiteral  );

reacctDotHooks: REACT DOT hook  SEMICOLON;

//reactHooks: REACT_HOOKS OPENPAREN (INTEGER | parameters | arrowFunction) CLOSEPAREN;
//hook: USE_STATE | USE_EFFECT | USE_CALLBACK 3 | USE_CONTEXT | USE_REF;

hook: useState | useEffect | useCallback | useContext | useRef | USE_STATE;
useState: CONST? pairValue? USE_STATE OPENPAREN (INTEGER | parameters | arrowFunction)? CLOSEPAREN SEMICOLON? ;
pairValue:OPENBRACKET ID COMMA ID CLOSEBRACKET EQUAL;

useEffect: USE_EFFECT OPENPAREN arrowFunction? block? CLOSEPAREN SEMICOLON?;
clickHandler: CONST CLICK_HANDLER EQUAL arrowFunction;
useCallback: USE_CALLBACK OPENPAREN parameters? arrowFunction? CLOSEPAREN SEMICOLON? ;

useContext:  USE_CONTEXT OPENPAREN ID CLOSEPAREN SEMICOLON? ;

useRef: USE_REF OPENPAREN INTEGER? CLOSEPAREN ;


////JSX

jsxBlock: OPENPAREN jsxElement CLOSEPAREN;

jsxElement:jsxOpenTag  jsxChildren closeTag;

jsxChildren:(jsxElement | jsxOpenSelfClose | jsxExpreeion | elementJs | jsxText)+;

jsxOpenTag:LT ID (attribute)* (jsxClass | attributeClick | style)? GT;

closeTag:LT DIVIDE ID GT;

jsxClass:JSX_CLASS EQUAL StringLiteral;

style:STYLE EQUAL props ;

attribute:ID EQUAL StringLiteral;

jsxOpenSelfClose:LT ID jsxClass?  attribute+  DIVIDE GT;

attributeClick:ON_CLICK EQUAL jsxExpreeion ;

attributeElementJs:ID EQUAL jsxExpreeion;

jsxExpreeion:OPENBRACE ID CLOSEBRACE ;

elementJs:LT ID attributeElementJs* DIVIDE GT;

jsxText: ~('<'|'>'|'{'|'}' | ')' | '(' )+;