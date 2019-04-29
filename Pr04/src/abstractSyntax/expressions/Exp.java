package abstractSyntax.expressions;


public abstract class Exp {
	protected TipoExp tipo;
	public TipoExp tipo () {return tipo;}
	
	public Exp opnd() {throw new UnsupportedOperationException("opnd");}
	public Exp opnd1() {throw new UnsupportedOperationException("opnd1");}
	public Exp opnd2() {throw new UnsupportedOperationException("opnd2");}
	
	public String id() {throw new UnsupportedOperationException("id");}
	public String boolVal() {throw new UnsupportedOperationException("boolVal");}
	public String numVal() {throw new UnsupportedOperationException("numVal");}
}
