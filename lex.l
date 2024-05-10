%{
#include <stdio.h>
%}

%option noyywrap

KEYWORD if|else|int|include
DIGIT [0-9]
LETTER [a-zA-Z]
OPERATOR [+*/-=]

%%
{DIGIT}+    { printf("CONSTANT: %s\n", yytext); }
{KEYWORD}   { printf("KEYWORD: %s\n", yytext); }
{LETTER}({LETTER}|{DIGIT}|[_])* { printf("IDENTIFIER: %s\n", yytext); } 
{OPERATOR}  { printf("OPERATOR: %s\n", yytext); }
%%

int main() {
    yylex();
    return 0;
}