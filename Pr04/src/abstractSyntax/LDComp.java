package abstractSyntax;

public class LDComp extends LD {
	
	private LD resto;
	
	public LDComp (String tipoVar, String id, LD resto) {
		super(tipoVar, id);
		this.resto = resto;
		this.tipo = TipoLD.COMP;
	}
	
	public LD resto () {return resto;}
	
	
	@Override
	public String toString() {
		return "DeclaracionCompuesta{" + tipoVar + ", " + id + ", " + resto + "}";
	}
	
}
