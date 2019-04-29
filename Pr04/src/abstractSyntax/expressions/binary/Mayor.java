package abstractSyntax.expressions.binary;

import abstractSyntax.expressions.Exp;
import abstractSyntax.expressions.TipoExp;

public class Mayor extends ExpBinaria {

	public Mayor(Exp opnd1, Exp opnd2) {
		super(opnd1, opnd2);
		this.tipo = TipoExp.MAYOR;
	}
	
	
	@Override
	public String toString() {
		return "Mayor" + super.toString();
	}
}
