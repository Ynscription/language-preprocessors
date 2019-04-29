package abstractSyntax.expressions.binary;

import abstractSyntax.expressions.Exp;
import abstractSyntax.expressions.TipoExp;

public class Igual extends ExpBinaria {

	public Igual(Exp opnd1, Exp opnd2) {
		super(opnd1, opnd2);
		this.tipo = TipoExp.IGUAL;
	}
	
	
	@Override
	public String toString() {
		return "Igual" + super.toString();
	}

}
