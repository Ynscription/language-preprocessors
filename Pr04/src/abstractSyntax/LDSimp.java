package abstractSyntax;

public class LDSimp extends LD {
	
	public LDSimp (String tipoVar, String id) {
		super(tipoVar, id);
		this.tipo = TipoLD.SIMP;
	}
	
	
	@Override
	public String toString() {
		return "DeclaracionSimple{" + tipoVar + id + "}";
	}
}
