package ascending.lexical;

import ascending.error.ErrorManager;

public class LexerOps {
	
	private Lexer lexer;
	public LexerOps(Lexer lexer) {
		this.lexer = lexer;
	}

	public LexicalUnit unitEof() {
		return new LexicalUnit(LexicalClass.EOF, "EOF", lexer.row());
	}

	public LexicalUnit unitMas() {
		return new LexicalUnit(LexicalClass.OP_ADD, "+", lexer.row());
	}

	public LexicalUnit unitNumber() {
		return new LexicalUnit(LexicalClass.LITERAL_NUMBER, "LIT_NUM", lexer.lexeme(), lexer.row());
	}

	public void error() {
		ErrorManager.lexicalError(lexer.row(), lexer.lexeme());
	}

	public LexicalUnit unitID() {
		return new LexicalUnit(LexicalClass.IDENTIFIER,"ID", lexer.lexeme(), lexer.row());
	}

	public LexicalUnit unitMenos() {
		return new LexicalUnit(LexicalClass.OP_SUB, "-", lexer.row());
	}

	public LexicalUnit unitAbrePar() {
		return new LexicalUnit(LexicalClass.OPEN_PARENTHESIS, "(", lexer.row());
	}

	public LexicalUnit unitCierraPar() {
		return new LexicalUnit(LexicalClass.CLOSE_PARENTHESIS, ")", lexer.row());
	}

	public LexicalUnit unitSepLin() {
		return new LexicalUnit(LexicalClass.SEMICOLON, ";", lexer.row());
	}

	public LexicalUnit unitAsignacion() {
		return new LexicalUnit(LexicalClass.ASSIGNMENT, "=", lexer.row());
	}

	public LexicalUnit unitMayor() {
		return new LexicalUnit(LexicalClass.OP_GREATER, ">", lexer.row());
	}

	public LexicalUnit unitMenor() {
		return new LexicalUnit(LexicalClass.OP_LESSER, "<", lexer.row());
	}

	public LexicalUnit unitDiv() {
		return new LexicalUnit(LexicalClass.OP_DIV, "/", lexer.row());
	}

	public LexicalUnit unitPor() {
		return new LexicalUnit(LexicalClass.OP_MUL, "*", lexer.row());
	}

	public LexicalUnit unitSepSec() {
		return new LexicalUnit(LexicalClass.SECTION_SEPARATOR, "&&", lexer.row());
	}

	public LexicalUnit unitIgual() {
		return new LexicalUnit(LexicalClass.OP_EQUALS, "==", lexer.row());
	}

	public LexicalUnit unitDistinto() {
		return new LexicalUnit(LexicalClass.OP_DIFFERENT, "!=", lexer.row());
	}

	public LexicalUnit unitMayorIgual() {
		return new LexicalUnit(LexicalClass.OP_GREATER_EQUAL, ">=", lexer.row());
	}

	public LexicalUnit unitMenorIgual() {
		return new LexicalUnit(LexicalClass.OP_LESSER_EQUAL, "<=", lexer.row());
	}

	public LexicalUnit unitOr() {
		return new LexicalUnit(LexicalClass.OP_OR, "OR", lexer.row());
	}

	public LexicalUnit unitNum() {
		return new LexicalUnit(LexicalClass.NUM, "NUM", lexer.row());
	}

	public LexicalUnit unitNot() {
		return new LexicalUnit(LexicalClass.OP_NOT, "NOT", lexer.row());
	}

	public LexicalUnit unitAnd() {
		return new LexicalUnit(LexicalClass.OP_AND, "AND", lexer.row());
	}

	public LexicalUnit unitBool() {
		return new LexicalUnit(LexicalClass.BOOL, "BOOL", lexer.row());
	}

	public LexicalUnit unitTrue() {
		return new LexicalUnit(LexicalClass.VALUE_TRUE, "TRUE", lexer.row());
	}

	public LexicalUnit unitFalse() {
		return new LexicalUnit(LexicalClass.VALUE_FALSE, "FALSE", lexer.row());
	}

}
