package abstractSyntax.expressions.binary;

import abstractSyntax.expressions.Exp;

public abstract class ExpBinaria extends Exp {
	private Exp opnd1;
	private Exp opnd2;
	
	public ExpBinaria (Exp opnd1, Exp opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public Exp opnd1() {return opnd1;};
	public Exp opnd2() {return opnd2;};
}
