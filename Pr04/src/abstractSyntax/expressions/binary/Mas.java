package abstractSyntax.expressions.binary;

import abstractSyntax.expressions.Exp;
import abstractSyntax.expressions.TipoExp;

public class Mas extends ExpBinaria {

	public Mas(Exp opnd1, Exp opnd2) {
		super(opnd1, opnd2);
		this.tipo = TipoExp.MAS;
	}
	
	
	@Override
	public String toString() {
		return "Mas" + super.toString();
	}
	
}
