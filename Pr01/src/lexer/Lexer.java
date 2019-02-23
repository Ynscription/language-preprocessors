package lexer;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
//#Constants
	
	private static final String NEW_LINE = System.lineSeparator();
	
//#Enums
	
	private static enum State {
		START,
		
		REC_ADD,
		REC_SUB,
		REC_INTEGER,
		REC_DECIMAL,
		REC_EXPONENTIAL,
		
		REC_IDENTIFIER,
		
		REC_ASSINGMENT,
		
		REC_EQUALS,
		REC_MUL,
		REC_DIV,
		REC_LESSER,
		REC_LESSER_EQUAL,
		REC_GREATER,
		REC_GREATER_EQUAL,
		REC_DIFFERENT,
		
		REC_OPEN_PARENTHESIS,
		REC_CLOSE_PARENTHESIS,
		
		REC_SEMICOLON,
		REC_SECTION_SEPARATOR,
		
		DECIMAL_DOT,
		EXPONENTIAL,
		EXPONENTIAL_SIGNED,
		DIFFERENT,
		SECTION_SEPARATOR,
		
		REC_EOF
	}
	
//#Attributes
	
	private Reader _input;
	
	private StringBuffer _lex;
	private int _next_char;
	
	private int _start_row;
	private int _start_column;
	private int _curr_row;
	private int _curr_column;
	
	private State _curr_state;
	
//#Accessors
	public int getCurrRow () { return _curr_row; }
	public int getCurrColumn () { return _curr_column; }
	
//#Constructors
	
	public Lexer (Reader input) throws IOException{
		_input = input;
		_lex = new StringBuffer ();
		_next_char = input.read();
		_curr_row = 1;
		_curr_column = 1;
	}
	
//#Public Methods
	
	/* Returns the next lexical unit
	 * If no lexical unit could be found returns null
	 * If an I/O error occurs, throws an IOException
	 */
	public LexicalUnit nextToken () throws IOException{
		LexicalUnit result = null;
		boolean success = true;
		_curr_state = State.START;
		_start_row = _curr_row;
		_start_column = _curr_column;
		_lex.delete(0, _lex.length());
		while (success) {
			if (_curr_state == State.START) {
				success = transitionStart();				
			}
			else if (_curr_state == State.REC_INTEGER) {
				if (! (success = transitionRecInt()))
					result = new LexicalUnit(LexicalClass.LITERAL_NUMBER, _lex.toString(), _start_row, _start_column);
			}
			else if (_curr_state == State.REC_ADD) {
				if (! (success = transitionRecAdd()))
					result = new LexicalUnit(LexicalClass.OP_ADD, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_SUB) {
				if (! (success = transitionRecSub()))
					result = new LexicalUnit(LexicalClass.OP_SUB, _start_row, _start_column);
			}
			else if (_curr_state == State.DECIMAL_DOT) {
				success = transitionDecDot();
			}
			else if (_curr_state == State.REC_DECIMAL) {
				if (! (success = transitionRecDec()))
					result = new LexicalUnit(LexicalClass.LITERAL_NUMBER, _lex.toString(), _start_row, _start_column);
			}
			else if (_curr_state == State.EXPONENTIAL) {
				success = transitionExp();
			}
			else if (_curr_state == State.EXPONENTIAL_SIGNED) {
				success = transitionSigExp ();
			}
			else if (_curr_state == State.REC_EXPONENTIAL) {
				if (! (success = transitionRecExp()))
					result = new LexicalUnit(LexicalClass.LITERAL_NUMBER, _lex.toString(), _start_row, _start_column);
			}
			else if (_curr_state == State.REC_IDENTIFIER) {
				if (! (success = transitionRecId()))
					result = getIdUnit();
			}
			else if (_curr_state == State.SECTION_SEPARATOR) {
				success = transitionSecSep();
			}
			else if (_curr_state == State.REC_SECTION_SEPARATOR) {
				success = false;
				result = new LexicalUnit(LexicalClass.SECTION_SEPARATOR, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_SEMICOLON) {
				success = false;
				result = new LexicalUnit(LexicalClass.SEMICOLON, _start_row, _start_column);
			}
			else if (_curr_state == State.DIFFERENT) {
				success = transitionDiff();
			}
			else if (_curr_state == State.REC_DIFFERENT) {
				success = false;
				result = new LexicalUnit(LexicalClass.OP_DIFFERENT, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_GREATER) {
				if (! (success = transitionRecG()))
					result = new LexicalUnit(LexicalClass.OP_GREATER, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_GREATER_EQUAL) {
				success = false;
				result = new LexicalUnit(LexicalClass.OP_GRETER_EQUAL, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_LESSER) {
				if (! (success = transitionRecL()))
					result = new LexicalUnit(LexicalClass.OP_LESSER, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_LESSER_EQUAL) {
				success = false;
				result = new LexicalUnit(LexicalClass.OP_LESSER_EQUAL, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_DIV) {
				success = false;
				result = new LexicalUnit(LexicalClass.OP_DIV, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_MUL) {
				success = false;
				result = new LexicalUnit(LexicalClass.OP_MUL, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_ASSINGMENT) {
				if (! (success = transitionRecAss()))
					result = new LexicalUnit(LexicalClass.ASSIGNEMENT, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_EQUALS) {
				success = false;
				result = new LexicalUnit(LexicalClass.OP_EQUALS, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_OPEN_PARENTHESIS) {
				success = false;
				result = new LexicalUnit(LexicalClass.OPEN_PARENTHESIS, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_CLOSE_PARENTHESIS) {
				success = false;
				result = new LexicalUnit(LexicalClass.CLOSE_PARENTHESIS, _start_row, _start_column);
			}
			else if (_curr_state == State.REC_EOF) {
				success = false;
				result = new LexicalUnit(LexicalClass.EOF, _start_row, _start_column);
			}
		}		
		
		return result;
	}
	
	
//#Private Methods
	
	//#Transition functions of states	
	private boolean transitionStart () throws IOException {
		boolean success = true;
		
		
		if (isDigit(_next_char))
			transitionTo(State.REC_INTEGER);			
		else if (isLetter(_next_char))
			transitionTo(State.REC_IDENTIFIER);
		
		else if (isPlus(_next_char))
			transitionTo(State.REC_ADD);		
		else if (isMinus(_next_char))
			transitionTo(State.REC_SUB);
		else if (isMul(_next_char))
			transitionTo(State.REC_MUL);
		else if (isDiv(_next_char))
			transitionTo(State.REC_DIV);
		
		else if (isLess(_next_char))
			transitionTo(State.REC_LESSER);		
		else if (isMore(_next_char))
			transitionTo(State.REC_GREATER);
		else if (isEqu(_next_char))
			transitionTo(State.REC_ASSINGMENT);
		else if (isNot(_next_char))
			transitionTo(State.DIFFERENT);
		
		else if (isOpPar(_next_char))
			transitionTo(State.REC_OPEN_PARENTHESIS);
		else if (isClPar(_next_char))
			transitionTo(State.REC_CLOSE_PARENTHESIS);
		
		else if (isUppersAnd(_next_char))
			transitionTo(State.SECTION_SEPARATOR);		
		else if (isSemicolon(_next_char))
			transitionTo(State.REC_SEMICOLON);
		
		else if (isWhiteSpace(_next_char))
			transitionIgnoringTo(State.START);
		
		else if (isEOF(_next_char))
			transitionTo(State.REC_EOF);
		else
			success = false;
		
		return success;
	}

	private boolean transitionRecInt () throws IOException {
		boolean success = true;
		
		if (isDigit(_next_char))
			transitionTo(State.REC_INTEGER);
		else if (isDecDot(_next_char))
			transitionTo(State.DECIMAL_DOT);
		else if (isExponential(_next_char))
			transitionTo(State.EXPONENTIAL);
		else
			success = false;		
		
		return success;
	}
	
	private boolean transitionRecAdd () throws IOException {
		boolean success = true;
		
		if (isDigit(_next_char))
			transitionTo(State.REC_INTEGER);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionRecSub () throws IOException {
		boolean success = true;
		
		if (isDigit(_next_char))
			transitionTo(State.REC_INTEGER);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionDecDot () throws IOException {
		boolean success = true;
		
		if (isDigit(_next_char))
			transitionTo(State.REC_DECIMAL);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionRecDec () throws IOException {
		boolean success = true;
		
		if (isDigit(_next_char))
			transitionTo(State.REC_DECIMAL);
		else if (isExponential(_next_char))
			transitionTo(State.EXPONENTIAL);
		else
			success = false;		
		
		return success;
	}
	
	private boolean transitionExp () throws IOException {
		boolean success = true;
		
		if (isDigit(_next_char))
			transitionTo(State.REC_EXPONENTIAL);
		else if (isPlus(_next_char) || isMinus(_next_char))
			transitionTo(State.EXPONENTIAL_SIGNED);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionSigExp () throws IOException {
		boolean success = true;
		
		if (isDigit(_next_char))
			transitionTo(State.REC_EXPONENTIAL);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionRecExp () throws IOException {
		boolean success = true;
		
		if (isDigit(_next_char))
			transitionTo(State.REC_EXPONENTIAL);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionRecId () throws IOException {
		boolean success = true;
		
		if (isDigit(_next_char) || isLetter(_next_char) || isUnderScore(_next_char))
			transitionTo(State.REC_IDENTIFIER);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionSecSep () throws IOException {
		boolean success = true;
		
		if (isUppersAnd(_next_char))
			transitionTo(State.REC_SECTION_SEPARATOR);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionDiff () throws IOException {
		boolean success = true;
		
		if (isEqu(_next_char))
			transitionTo(State.REC_DIFFERENT);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionRecG () throws IOException {
		boolean success = true;
		
		if (isEqu(_next_char))
			transitionTo(State.REC_GREATER_EQUAL);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionRecL () throws IOException {
		boolean success = true;
		
		if (isEqu(_next_char))
			transitionTo(State.REC_LESSER_EQUAL);
		else
			success = false;
		
		return success;
	}
	
	private boolean transitionRecAss () throws IOException {
		boolean success = true;
		
		if (isEqu(_next_char))
			transitionTo(State.REC_EQUALS);
		else
			success = false;
		
		return success;
	}
	
	
	
	
	//#Reserved word resolver	
	private LexicalUnit getIdUnit () {
		LexicalUnit result = null;
		if (_lex.toString().equals("num"))
			result = new LexicalUnit(LexicalClass.NUM, _start_row, _start_column);
		else if (_lex.toString().equals("bool"))
			result = new LexicalUnit(LexicalClass.BOOL, _start_row, _start_column);
		else if (_lex.toString().equals("true"))
			result = new LexicalUnit(LexicalClass.VALUE_TRUE, _start_row, _start_column);
		else if (_lex.toString().equals("false"))
			result = new LexicalUnit(LexicalClass.VALUE_FALSE, _start_row, _start_column);
		else if (_lex.toString().equals("and"))
			result = new LexicalUnit(LexicalClass.OP_AND, _start_row, _start_column);
		else if (_lex.toString().equals("or"))
			result = new LexicalUnit(LexicalClass.OP_OR, _start_row, _start_column);
		else if (_lex.toString().equals("not"))
			result = new LexicalUnit(LexicalClass.OP_NOT, _start_row, _start_column);
		else
			result = new LexicalUnit(LexicalClass.IDENTIFIER, _lex.toString(), _start_row, _start_column);
		return result;
	}
	
	
	
	//#Transition functions between states	
	public void transitionTo (State next_state) throws IOException {
		_lex.append((char)_next_char);
		readNextChar();
		_curr_state = next_state;
	}
	public void transitionIgnoringTo (State next_state) throws IOException {
		readNextChar();
		_start_row = _curr_row;
		_start_column = _curr_row;
		_curr_state = next_state;
			
	}
	private void readNextChar () throws IOException {
		_next_char = _input.read();
		/*if (NEW_LINE.indexOf(_nextChar) != -1) {//If next char is a character from line separator
		    skipEndLine();
			_curr_row++;
			_curr_column = 0;
		}
		else {
			_curr_column++;
		}*/
		if(_next_char == NEW_LINE.charAt(0)) {
		    skipEndLine();
		    _curr_row++;
		    _curr_column = 0;
        }
        else
            _curr_column++;
		
	}
	private void skipEndLine() throws IOException {
		int aux;
		for (int i = 1; i < NEW_LINE.length(); i++) {
			aux = _input.read();
			if (aux != NEW_LINE.charAt(i))
				throw new IOException("Unexpected endline character found at line " + _curr_row);
		}
	}
	
	//#Character recognition functions	
	private static boolean isUppersAnd (int c)		{ return c == '&'; }
	private static boolean isSemicolon (int c)		{ return c == ';'; }
	
	private static boolean isLetter (int c) 		{ return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'; }
	private static boolean isDigit (int c) 			{ return c >= '0' && c <= '9'; }
	private static boolean isUnderScore (int c) 	{ return c == '_'; }
	
	private static boolean isDecDot (int c) 		{ return c == '.'; }
	private static boolean isExponential (int c) 	{ return c == 'e' || c == 'E'; }
	
	private static boolean isPlus (int c) 			{ return c == '+'; }
	private static boolean isMinus (int c) 			{ return c == '-'; }
	private static boolean isMul (int c) 			{ return c == '*'; }
	private static boolean isDiv (int c) 			{ return c == '/'; }
	
	private static boolean isEqu (int c)			{ return c == '='; }
	private static boolean isLess (int c)			{ return c == '<'; }
	private static boolean isMore (int c)			{ return c == '>'; }
	private static boolean isNot (int c)			{ return c == '!'; }
	
	private static boolean isOpPar (int c)			{ return c == '('; }
	private static boolean isClPar (int c)			{ return c == ')'; }
	
	private static boolean isNewLine (int c)		{ return c == NEW_LINE.charAt(0);}
	private static boolean isWhiteSpace (int c)		{ return c == ' ' || c == '\t' || isNewLine(c); }
	private static boolean isEOF (int c) 			{return c == -1; }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

