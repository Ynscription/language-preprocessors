package abstractSyntax;

public class LDComp extends LD {
	private String id;
	private String tipoVar;
	private LD resto;
	
	public LDComp (String id, String tipoVar, LD resto) {
		this.id = id;
		this.tipoVar = tipoVar;
		this.resto = resto;
		this.tipo = TipoLD.COMP;
	}
	
	
	public String id () {return id;}
	public String tipoVar () {return tipoVar;}
	public LD resto () {return resto;}
	
	
	
}
