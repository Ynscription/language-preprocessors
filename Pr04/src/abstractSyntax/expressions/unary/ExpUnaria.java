package abstractSyntax.expressions.unary;

import abstractSyntax.expressions.Exp;

public abstract class ExpUnaria extends Exp {
	protected Exp opnd;
	
	public ExpUnaria (Exp opnd) {
		this.opnd = opnd;
	}
	
	public Exp opnd() {return opnd;};
}
