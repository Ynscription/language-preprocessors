package abstractSyntax;

public abstract class LD {
	protected TipoLD tipo;
	public TipoLD tipo () {return tipo;}

	public String id () {throw new UnsupportedOperationException("id");}
	public String tipoVar () {throw new UnsupportedOperationException("tipoVar");}
	public LD resto () {throw new UnsupportedOperationException("resto LD");}
}
