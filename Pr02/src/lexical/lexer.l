package lexical;

%%
%line
%class Lexer
%type  LexicalUnit
%unicode

%{
  private LexerOps ops;
  public String lexeme() {return yytext();}
  public int row() {return yyline+1;}
%}

%eofval{
  return ops.unitEof();
%eofval}

%init{
  ops = new LexerOps(this);
%init}

letra  = ([A-Z]|[a-z])
dig = [0-9]
p_ent = [\+\-]?{dig}+
p_dec = \.{dig}+
p_exp = [\e\E]{p_ent}
num = "num"
bool = "bool"
punt_coma = \;
ID = {letra}({letra}|{dig}|\_)*
sep_sec = "&&"
asignacion = \=
op_mas = \+
op_menos = \-
op_por = \*
op_div = \/
op_and = "and"
op_or = "or"
op_not = "not"
op_menor_que = \<
op_mayor_que = \>
op_menor_igual = \<\=
op_mayor_igual = \>\=
op_igual = \=\=
op_distinto = \!\=
lit_num = {p_ent}({p_dec}|{p_exp}|{p_dec}{p_exp})?
val_true = "true"
val_false = "false"
abre_par = \(
cierra_par = \)
sep = [\t\b\n\r" "]
%%

{sep}                       {}
{lit_num}                   {return ops.unitNumber();}
{op_menos}                  {return ops.unitMenos();}
{op_mas}                    {return ops.unitMas();}
{abre_par}                  {return ops.unitAbrePar();}
{cierra_par}                {return ops.unitCierraPar();}
{sep_sec}                   {return ops.unitSepSec();}
{punt_coma}                 {return ops.unitSepLin();}
{op_distinto}               {return ops.unitDistinto();}
{op_mayor_que}              {return ops.unitMayor();}
{op_mayor_igual}            {return ops.unitMayorIgual();}
{op_menor_que}              {return ops.unitMenor();}
{op_menor_igual}            {return ops.unitMenorIgual();}
{op_div}                    {return ops.unitDiv();}
{op_por}                    {return ops.unitPor();}
{op_igual}                  {return ops.unitIgual();}
{asignacion}                {return ops.unitAsignacion}

{num}                       {return ops.unitNum();}
{bool}                      {return ops.unitBool();}
{op_and}                    {return ops.unitAnd();}
{op_or}                     {return ops.unitOr();}
{op_not}                    {return ops.unitNot();}
{val_true}                  {return ops.unitTrue();}
{val_false}                 {return ops.unitFalse();}
{ID}                        {return ops-unitID();}

[^]                       {ops.error();}
