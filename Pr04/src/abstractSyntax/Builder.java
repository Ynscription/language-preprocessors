package abstractSyntax;

import abstractSyntax.expressions.*;
import abstractSyntax.expressions.unary.*;
import abstractSyntax.expressions.binary.*;

public class Builder {
	public static Program prog (LD decs, LI insts) {return new Program(decs, insts);}
	
	public static LD ldCompuesta (String tipoVar, String id, LD resto) {return new LDComp(tipoVar, id, resto);}
	public static LD ldSimple (String tipoVar, String id) {return new LDSimp(tipoVar, id);}
	public static LI liCompuesta (String id, Exp exp, LI resto) {return new LIComp(id, exp, resto);}
	public static LI liSimple (String id, Exp exp) {return new LISimp(id, exp);}
	
	public static Exp id (String id) {return new Id(id);}
	public static Exp num (String v) {return new Num(v);}
	public static Exp bool (String v) {return new Bool(v);}
	
	public static Exp menosUn (Exp opnd) {return new MenosUn(opnd);}
	public static Exp not (Exp opnd) {return new Not(opnd);}
	
	public static Exp mas (Exp opnd1, Exp opnd2) {return new Mas(opnd1, opnd2);}
	public static Exp menos (Exp opnd1, Exp opnd2) {return new Menos(opnd1, opnd2);}
	public static Exp mul (Exp opnd1, Exp opnd2) {return new Mul(opnd1, opnd2);}
	public static Exp div (Exp opnd1, Exp opnd2) {return new Div(opnd1, opnd2);}
	
	public static Exp and (Exp opnd1, Exp opnd2) {return new And(opnd1, opnd2);}
	public static Exp or (Exp opnd1, Exp opnd2) {return new Or(opnd1, opnd2);}
	
	public static Exp igual (Exp opnd1, Exp opnd2) {return new Igual(opnd1, opnd2);}
	public static Exp distinto (Exp opnd1, Exp opnd2) {return new Distinto(opnd1, opnd2);}
	
	public static Exp mayor (Exp opnd1, Exp opnd2) {return new Mayor(opnd1, opnd2);}
	public static Exp mayorIgual (Exp opnd1, Exp opnd2) {return new MayorIgual(opnd1, opnd2);}
	public static Exp menor (Exp opnd1, Exp opnd2) {return new Menor(opnd1, opnd2);}
	public static Exp menorIgual (Exp opnd1, Exp opnd2) {return new MenorIgual(opnd1, opnd2);}
	
	
	public static Exp mkexp (String op, Exp opnd1, Exp opnd2) {
		switch(op) {
			case "+": return mas (opnd1, opnd2);
			case "-": return menos (opnd1, opnd2);
			case "!=": return distinto(opnd1, opnd2);
			case "==": return igual(opnd1, opnd2);
			case "<": return menor(opnd1, opnd2);
			case "<=": return menorIgual(opnd1, opnd2);
			case ">": return mayor(opnd1, opnd2);
			case ">=": return mayorIgual(opnd1, opnd2);
			case "*": return mul(opnd1, opnd2);
			case "/": return div(opnd1, opnd2);
			default: return null;
		}
	}
	
}
