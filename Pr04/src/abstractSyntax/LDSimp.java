package abstractSyntax;

public class LDSimp extends LD {
	private String id;
	private String tipoVar;
	
	public LDSimp (String id, String tipoVar) {
		this.id = id;
		this.tipoVar = tipoVar;
		this.tipo = TipoLD.SIMP;
	}
	
	public String id () {return id;}
	public String tipoVar () {return tipoVar;}
}
