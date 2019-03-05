package lexical;

public class LexicalUnit {
	//#Attributes
	private LexicalClass _lex_class;
	private String _lexeme;
	private int _row;
	private int _column;
	
	
	//#Accessors
	public LexicalClass getLexClass () { return _lex_class; }
	public String getLexeme () { return _lexeme; }
	
	public int getRow () { return _row; }
	public int getColumn () { return _column; }	
	
	
	//#Constructors
	public LexicalUnit (LexicalClass lex_class, int row, int column) {
		_lex_class = lex_class;
		_lexeme = null;
		_row = row;
		_column = column;		
	}
	
	public LexicalUnit (LexicalClass lex_class, String lexeme, int row, int column) {
		_lex_class = lex_class;
		_lexeme = lexeme;
		_row = row;
		_column = column;		
	}
	
	
	@Override
	public String toString() {
		String lexeme = "";
		if (_lexeme != null)
			lexeme = " (" + _lexeme + ")";
		return _lex_class.toString() + lexeme + " @" +_row + ":" + _column;
	}
}
