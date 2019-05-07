package ascending.lexical;

import java_cup.runtime.Symbol;

public class LexicalUnit extends Symbol{
	//#Attributes
	private int _row;
	private Integer _column;
	private String _className;
	
	
	//#Accessors
	public int getLexClass () { return sym; }
	public String getLexeme () { return (String)value; }
	
	public int getRow () { return _row; }
	public int getColumn () { return _column; }	
	
	
	//#Constructors
	public LexicalUnit (int lex_class, String className, String lexeme, int row) {
		super(lex_class, lexeme);
		_className = className;
		_row = row;
		_column = null;		
	}
	
	public LexicalUnit (int lex_class, String className, String lexeme, int row, int column) {
		super(lex_class, lexeme);
		_className = className;
		_row = row;
		_column = column;		
	}
	public LexicalUnit (int lex_class, String lexeme, int row) {
		super(lex_class, lexeme);
		_row = row;
		_column = null;		
	}
	
	public LexicalUnit (int lex_class, String lexeme, int row, int column) {
		super(lex_class, lexeme);
		_row = row;
		_column = column;		
	}
	
	
	@Override
	public String toString() {
		String ret = "";
		if (_className != null)
			ret += _className + " (" + value + ")";
		else
			ret += value;
		ret += " @" +_row;
		if (_column != null)
			ret += ":" + _column;
		return ret;
	}
}
