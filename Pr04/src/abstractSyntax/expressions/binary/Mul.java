package abstractSyntax.expressions.binary;

import abstractSyntax.expressions.Exp;
import abstractSyntax.expressions.TipoExp;

public class Mul extends ExpBinaria {

	public Mul(Exp opnd1, Exp opnd2) {
		super(opnd1, opnd2);
		this.tipo = TipoExp.MUL;
	}
	
	
	@Override
	public String toString() {
		return "Multiplicacion" + super.toString();
	}

}
