package abstractSyntax;

import abstractSyntax.expressions.Exp;

public class LIComp extends LI {
	private LI resto;
	
	public LIComp (String id, Exp exp, LI resto) {
		super(id, exp);
		this.resto = resto;
		this.tipo = TipoLIs.COMP;
	}
	
	public LI resto () {return resto;}
	
	
	@Override
	public String toString() {
		return "InstruccionCompuesta{" + id + ", " + exp + ", " + resto + "}";
	}
}
