lexer grammar LexerGram;

//symbols
OpenBracket:                    '[';
CloseBracket:                   ']';
OpenParen:                      '(';
CloseParen:                     ')';
OpenBrace:                      '{';
CloseBrace:                     '}';
SemiColon:                      ';';
Comma:                          ',';
Assign:                         '=';
QuestionMark:                   '?';
QuestionMarkDot:                '?.';
Colon:                          ':';
Ellipsis:                       '...';
Dot:                            '.';
PlusPlus:                       '++';
MinusMinus:                     '--';
Plus:                           '+';
Minus:                          '-';
BitNot:                         '~';
Not:                            '!';
Multiply:                       '*';
Divide:                         '/';
Modulus:                        '%';

///////keywords
VOID: 'void';
INT: 'int';
DOUBLE: 'double';
STRING: 'String';
BOOL: 'bool';
TRUE_FALSE: 'true' | 'false';
NULL: 'null';
IF: 'if';
ELSE: 'else';
ELSEIF: 'else if';
WHILE: 'while';
CLASS: 'class';
RETURN: 'return';
NEW: 'new';
CONTINUE: 'continue';
BREAK: 'break';
THIS: 'this';
OVERRIDE: 'override';
EXTENDS: 'extends';
IMPORT: 'import';
FOR: 'for';

NUMBER : DIGIT+ ( '.' DIGIT+ )? ;

SingleLineString : StringDQ | 'r\'' (~('\'' | '\n' | '\r'))* '\'' | 'r"' (~('"' | '\n' | '\r'))* '"' ;
fragment StringDQ :
                   ('"' | '\'') StringContentDQ*? ('"' | '\'') ;
fragment StringContentDQ :
         ~('\\' | '"' | '\n' | '\r' | '$') | '\\' ~('\n' | '\r') | StringDQ | '${' StringContentDQ*? '}';

IDENTIFIER : IDENTIFIER_START IDENTIFIER_PART* ;
fragment IDENTIFIER_START : LETTER | '_' ;
fragment IDENTIFIER_PART : IDENTIFIER_START | DIGIT ;


fragment LETTER : [a-zA-Z] ;
fragment DIGIT : [0-9] ;

WHITESPACE : ('\t' | ' ')+ -> skip;
NEWLINE : ('\n' | '\r' | '\r\n') -> skip ;
COMMENT : '//'.*? '\n' -> skip;
MULTI_LINE_COMMENT: '/*' (MULTI_LINE_COMMENT | .)*? '*/' -> skip;