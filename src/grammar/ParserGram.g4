parser grammar ParserGram;

options {tokenVocab = LexerGram;}

/*program:  expression* EOF;*/

/*expression: function | function_call SC+ | decl
           | assign | conditional_if | while_loop | for_loop
           | block | class_declaration;*/
