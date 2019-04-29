package abstractSyntax;

import abstractSyntax.expressions.Exp;

public class LISimp extends LI {
	private String id;
	private Exp exp;
	
	public LISimp (String id, Exp exp) {
		this.id = id;
		this.exp = exp;
		this.tipo = TipoLIs.SIMP;
	}
	
	public String id () {return id;}
	public Exp exp () {return exp;};
}
