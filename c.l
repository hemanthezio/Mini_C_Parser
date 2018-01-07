/* LEX Specification for MINI C PARSER */
alpha [a-zA-Z]
digit [0-9]
alphanum [a-zA-z\.]
%%
[ \t]			;
[ \n]   	{ yylineno = yylineno + 1;}
int		return INT;
float 		return FLOAT;
char 		return CHAR;
void 		return VOID;
double 		return DOUBLE;
for 		return FOR;
while		return WHILE;
if		return IF;
do		return DO;
return  	return RETURN;
break		return BREAK;
define  	return DEFINE;
include 	return INCLUDE;
case 		return CASE;
switch		return SWITCH;
main		return MAIN;
default 	return DEFAULT;
continue 	return CONTINUE;
else		return ELSE;
printf  	return PRINTF;
struct 		return STRUCT;
union   	return UNION;
{digit}+      	return NUM;
{alpha}({alpha}|{digit})* return ID;
"<="   		return LE;
">="    	return GE;
"=="    	return EQ;
"!="    	return NE;
">"		return GT;
"<"		return LT;
\.     		return DOT;
[!%]		return ID;
\/\/.* 			 ;
\/\*(.*\n)*.*\*\/ 	 ;
.      		return yytext[0];
%%
