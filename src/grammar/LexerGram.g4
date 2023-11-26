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

//symbols
OPENBRACKET:                    '[';
CLOSEBRACKET:                   ']';
OPENPAREN:                      '(';
CLOSEPAREN:                     ')';
OPENBRACE:                      '{';
CLOSEBRACE:                     '}';
SEMICOLON:                      ';';
COMMA:                          ',';
ASSIGN:                         '=';
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
SINGLE_QUOTE: '\'';
DOUBLE_QUOTE: '"';



// Numeric literals
INTEGER: '0' | [1-9] [0-9]*;
FLOAT: INTEGER '.' [0-9]+;

//identifier
ID: [a-zA-Z_][a-zA-Z0-9_]*;

// String literal rule

StringLiteral : '\'' ( ~'\'' | '\'\'' )* '\''      // Single-quoted string
             | '"' ( ~'"' | '""' )* '"'           // Double-quoted string
             ;





WS: [ \t\r\n]+ -> skip;
WHITESPACE : ('\t' | ' ')+ -> skip;
NEWLINE : ('\n' | '\r' | '\r\n') -> skip ;
COMMENT : '//'.*? '\n' -> skip;
MULTI_LINE_COMMENT: '/*' (MULTI_LINE_COMMENT | .)*? '*/' -> skip;