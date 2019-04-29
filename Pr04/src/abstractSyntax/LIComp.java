package abstractSyntax;

import abstractSyntax.expressions.Exp;

public class LIComp extends LI {
	private String id;
	private Exp exp;
	private LI resto;
	
	public LIComp (String id, Exp exp, LI resto) {
		this.id = id;
		this.exp = exp;
		this.resto = resto;
		this.tipo = TipoLIs.SIMP;
	}
	
	public String id () {return id;}
	public Exp exp () {return exp;};
	public LI resto () {return resto;};
}
