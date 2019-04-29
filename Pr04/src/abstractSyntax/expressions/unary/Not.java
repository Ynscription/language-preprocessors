package abstractSyntax.expressions.unary;

import abstractSyntax.expressions.Exp;
import abstractSyntax.expressions.TipoExp;

public class Not extends ExpUnaria {

	public Not(Exp opnd) {
		super(opnd);
		this.tipo = TipoExp.NOT;
	}

}
