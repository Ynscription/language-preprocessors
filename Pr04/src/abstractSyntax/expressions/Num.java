package abstractSyntax.expressions;

public class Num extends Exp {
	private String v;
	
	public Num (String v) {
		this.v = v;
		this.tipo = TipoExp.NUM;
	}
	
	public String numVal() {return v;}
}
