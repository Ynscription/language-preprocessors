package abstractSyntax;

import abstractSyntax.expressions.Exp;

public abstract class LI {
	protected TipoLIs tipo;
	protected String id;
	protected Exp exp;	
	
	public LI(String id, Exp exp) {
		this.id = id;
		this.exp = exp;
	}
	
	public String id () {return id;}
	public Exp exp () {return exp;}
	public TipoLIs tipo () {return tipo;}
	
	public LI resto () {throw new UnsupportedOperationException("resto LI");}
}
