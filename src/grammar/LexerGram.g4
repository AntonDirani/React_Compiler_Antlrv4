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
REACT:  'React';
//REACT_HOOKS: 'useState' | 'useEffect' | 'useContext' | 'useRef';
USE_STATE          : 'useState';
USE_EFFECT         : 'useEffect';
USE_CALLBACK       : 'useCallback';
USE_CONTEXT        : 'useContext';
USE_REF            : 'useRef';
JSX_CLASS:'className';
ON_CLICK:'onClick';
SRC : 'src';
ALT: 'alt';
//CONSOLE_LOG        : 'console.log';
CLICK_HANDLER: 'clickHandler';
PROP: 'prop';
STYLE: 'style';
DISPLAY: 'display';
FLEX_FLOW: 'flexFlow';
JUSTIFY: 'justifyContent';
ALIGN_CONTENT: 'alignContent';
//APP: 'App';

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
CREATE_ELEMENT: 'createElement';

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


HTML_TAGS_ELEMENT: WS* ('\'h1\'' | '\'h2\'' | '\'h3\'' | '\'p\'' | '\'span\'' | '\'div\'' | '\'button\'' | '\'img\'') WS*;

// String literal rule
StringLiteral : '\'' ( ~'\'' | '\'\'' )* '\''      // Single-quoted string
             | '"' ( ~'"' | '""' )* '"' // Double-quoted string
             ;
//Bool: TRUE | FALSE;

OPEN_TAG:LT HTML_TAGS ;

OPEN_TAG_SELF:LT HTML_TAGS_SPECIAL ;

CLOSE_TAG:LT DIVIDE HTML_TAGS GT;
CLOSE_TAG_SELF: DIVIDE GT;
//HTML_TAGS:ID;

HTML_TAGS: WS* ('h1' | 'h2' | 'h3' | 'p' | 'span' | 'div' | 'button') WS*;


HTML_TAGS_SPECIAL:'img'| 'link';


//ATTRIBUTES_JSX:  ('className') ;

WS: [ \t\r\n]+ -> skip;
WHITESPACE : ('\t' | ' ')+ -> skip;
NEWLINE : ('\n' | '\r' | '\r\n') -> skip ;
COMMENT : '//'.*? '\n' -> skip;
MULTI_LINE_COMMENT: '/*' (MULTI_LINE_COMMENT | .)*? '*/' -> skip;