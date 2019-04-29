package abstractSyntax;

public abstract class LD {
	protected TipoLD tipo;
	protected String tipoVar;
	protected String id;	
	
	public LD (String tipoVar, String id) {
		this.tipoVar = tipoVar;
		this.id = id;
	}

	public TipoLD tipo () {return tipo;}
	public String id () {return id;}
	public String tipoVar () {return tipoVar;}
	
	public LD resto () {throw new UnsupportedOperationException("resto LD");}
}
