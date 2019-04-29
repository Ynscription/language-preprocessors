package abstractSyntax;

import abstractSyntax.expressions.Exp;

public abstract class LI {
	protected TipoLIs tipo;
	public TipoLIs tipo () {return tipo;}
	
	public String id () {throw new UnsupportedOperationException("id");}
	public Exp exp () {throw new UnsupportedOperationException("exp");};
	public LI resto () {throw new UnsupportedOperationException("resto LI");}
}
