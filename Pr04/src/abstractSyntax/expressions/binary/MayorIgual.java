package abstractSyntax.expressions.binary;

import abstractSyntax.expressions.Exp;
import abstractSyntax.expressions.TipoExp;

public class MayorIgual extends ExpBinaria {

	public MayorIgual(Exp opnd1, Exp opnd2) {
		super(opnd1, opnd2);
		this.tipo = TipoExp.MAYOR_IGUAL;
	}
	
	
	@Override
	public String toString() {
		return "MayorIgual" + super.toString();
	}

}
