package abstractSyntax.expressions.unary;

import abstractSyntax.expressions.Exp;
import abstractSyntax.expressions.TipoExp;

public class MenosUn extends ExpUnaria {

	public MenosUn(Exp opnd) {
		super(opnd);
		this.tipo = TipoExp.MENOS_UN;
	}	
	
	
	@Override
	public String toString() {
		return "MenosUnario{" + opnd + "}";
	}

}
