package abstractSyntax;

import abstractSyntax.expressions.*;
import abstractSyntax.expressions.unary.*;
import abstractSyntax.expressions.binary.*;

public class Builder {
	public LD ldCompuesta (String tipoVar, String id, LD resto) {return new LDComp(tipoVar, id, resto);}
	public LD ldSimple (String tipoVar, String id) {return new LDSimp(tipoVar, id);}
	public LI liCompuesta (String id, Exp exp, LI resto) {return new LIComp(id, exp, resto);}
	public LI liSimple (String id, Exp exp) {return new LISimp(id, exp);}
	
	public Exp id (String id) {return new Id(id);}
	public Exp num (String v) {return new Num(v);}
	public Exp bool (String v) {return new Bool(v);}
	
	public Exp menosUn (Exp opnd) {return new MenosUn(opnd);}
	public Exp not (Exp opnd) {return new Not(opnd);}
	
	public Exp mas (Exp opnd1, Exp opnd2) {return new Mas(opnd1, opnd2);}
	public Exp menos (Exp opnd1, Exp opnd2) {return new Menos(opnd1, opnd2);}
	public Exp mul (Exp opnd1, Exp opnd2) {return new Mul(opnd1, opnd2);}
	public Exp div (Exp opnd1, Exp opnd2) {return new Div(opnd1, opnd2);}
	
	public Exp and (Exp opnd1, Exp opnd2) {return new And(opnd1, opnd2);}
	public Exp or (Exp opnd1, Exp opnd2) {return new Or(opnd1, opnd2);}
	
	public Exp igual (Exp opnd1, Exp opnd2) {return new Igual(opnd1, opnd2);}
	public Exp distinto (Exp opnd1, Exp opnd2) {return new Distinto(opnd1, opnd2);}
	
	public Exp mayor (Exp opnd1, Exp opnd2) {return new Mayor(opnd1, opnd2);}
	public Exp mayorIgual (Exp opnd1, Exp opnd2) {return new MayorIgual(opnd1, opnd2);}
	public Exp menor (Exp opnd1, Exp opnd2) {return new Menor(opnd1, opnd2);}
	public Exp menorIgual (Exp opnd1, Exp opnd2) {return new MenorIgual(opnd1, opnd2);}
	
	
}
