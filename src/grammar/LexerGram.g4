lexer grammar LexerGram;

// JS keywords
IMPORT: 'import';
FROM: 'from';
CLASS: 'class';
EXTENDS: 'extends';
SUPER: 'super';
CONSTRUCTOR: 'constructor';
RENDER: 'render';
RETURN: 'return';
EXPORT: 'export';
DEFAULT: 'default';
BREAK: 'break';
CASE: 'case';
CATCH: 'catch';
CONST: 'const';
CONTINUE: 'continue';
DEBUGGER: 'debugger';
DELETE: 'delete';
DO: 'do';
ELSE: 'else';
FINALLY: 'finally';
FOR: 'for';
FUNCTION: 'function';
IN: 'in';
INSTANCEOF: 'instanceof';
NEW: 'new';
SWITCH: 'switch';
THIS: 'this';
THROW: 'throw';
TRY: 'try';
TYPEOF: 'typeof';
VAR: 'var';
VOID: 'void';
WHILE: 'while';
WITH: 'with';
YIELD: 'yield';
LET: 'let';
LOG: 'log';
CONSOLE: 'console';
IF: 'if';
NULL: 'null';
PUBLIC: 'public';
PRIVATE: 'private';
PROTECTED: 'protected';
ABSTRACT: 'abstract';
STATIC: 'static';
REACT: 'react';
USE_STATE: 'useState';


//symbols
OPENBRACKET:                    '[';
CLOSEBRACKET:                   ']';
OPENPAREN:                      '(';
CLOSEPAREN:                     ')';
OPENBRACE:                      '{';
CLOSEBRACE:                     '}';
SEMICOLON:                      ';';
COMMA:                          ',';
EQUAL:                          '=';
QUESTIONMARK:                   '?';
QUESTIONMARKDOT:                '?.';
COLON:                          ':';
ELLIPSIS:                       '...';
DOT:                            '.';
PLUSPLUS:                       '++';
MINUSMINUS:                     '--';
PLUS:                           '+';
MINUS:                          '-';
BITNOT:                         '~';
NOT:                            '!';
MULTIPLY:                       '*';
DIVIDE:                         '/';
MODULUS:                        '%';
SDOLLAR:                        '$';
SINGLE_QUOTE: '\'';
DOUBLE_QUOTE: '"';
BOOL_TRUE_FALSE: 'true' | 'false';

//logical operators
AND: '&&';
OR: '||';
EQUALEQUAL: '==';
NOTEQUAL: '!=';

// Comparison operators
LT: '<';
GT: '>';
LTE: '<=';
GTE: '>=';
// Numeric literals
INTEGER: '0' | [1-9] [0-9]*;
FLOAT: INTEGER '.' [0-9]+;

//identifier
ID: [a-zA-Z_][a-zA-Z0-9_]*;

// String literal rule
StringLiteral : '\'' ( ~'\'' | '\'\'' )* '\''      // Single-quoted string
             | '"' ( ~'"' | '""' )* '"'
             | '\'' REACT '\'' // Double-quoted string
             ;
//Bool: TRUE | FALSE;

OPEN_TAG:LT HTML_TAGS;
OPEN_TAG_SELF:LT HTML_TAGS_SPECIAL;
OPEN_TAG_ATT:LT HTML_TAG_SPECIAL;
CLOSE_TAG:LT DIVIDE HTML_TAGS GT;
//HTML_TAGS:ID;

HTML_TAGS: WS* ('h1' | 'h2' | 'h3' | 'p' | 'span' | 'div') WS*;
HTML_TAGS_SPECIAL:'img'|'src'|'alt';
HTML_TAG_SPECIAL:'div';

ATTRIBUTES_JSX:  ('className') ;

WS: [ \t\r\n]+ -> skip;
WHITESPACE : ('\t' | ' ')+ -> skip;
NEWLINE : ('\n' | '\r' | '\r\n') -> skip ;
COMMENT : '//'.*? '\n' -> skip;
MULTI_LINE_COMMENT: '/*' (MULTI_LINE_COMMENT | .)*? '*/' -> skip;