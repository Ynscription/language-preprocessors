package abstractSyntax.expressions;

public class Id extends Exp {
	private String id;
	
	public Id (String id) {
		this.id = id;
		this.tipo = TipoExp.ID;
	}
	
	public String id() {return id;}
	
	
	@Override
	public String toString() {
		return "Identificador{" + id + "}";
	}
}
