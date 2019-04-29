package abstractSyntax.expressions.binary;

import abstractSyntax.expressions.Exp;
import abstractSyntax.expressions.TipoExp;

public class Or extends ExpBinaria {

	public Or(Exp opnd1, Exp opnd2) {
		super(opnd1, opnd2);
		this.tipo = TipoExp.OR;
	}
	
	
	@Override
	public String toString() {
		return "Or" + super.toString();
	}

}
