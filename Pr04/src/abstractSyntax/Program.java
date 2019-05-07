package abstractSyntax;

public class Program {
	private LD decs;
	private LI insts;
	
	public Program (LD decs, LI insts) {
		this.decs = decs;
		this.insts = insts;
	}
	
	public LD decs () {return decs;}
	public LI insts() {return insts;}
	
	@Override
	public String toString() {
		return "Program{" + decs + "," + System.lineSeparator() + insts + "}";
	}
}
