package abstractSyntax.expressions;

public class Bool extends Exp {
	private String v;
	
	public Bool (String v) {
		this.v = v;
		this.tipo = TipoExp.BOOL;
	}
	
	public String boolVal() {return v;}
	
	
	@Override
	public String toString() {
		return "Booleano{" + v + "}";
	}
}
