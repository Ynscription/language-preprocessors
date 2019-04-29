package abstractSyntax;

import abstractSyntax.expressions.Exp;

public class LISimp extends LI {
	
	public LISimp (String id, Exp exp) {
		super(id, exp);
		this.tipo = TipoLIs.SIMP;
	}
	
	
	@Override
	public String toString() {
		return "InstruccionSimple{" + id + ", " + exp + "}";
	}
}
