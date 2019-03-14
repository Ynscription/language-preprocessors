package syntactical;

import java.io.IOException;
import java.io.Reader;

import error.ErrorManager;
import lexical.*;

public class SyntaxAnalyzer {
	private LexicalUnit lookAhead;
	private Lexer lexer;
	
	public SyntaxAnalyzer (Reader fileIn) {
		lexer = new Lexer(fileIn);
		nextToken();
		
	}
	
	public void run() {
		Programa();
	}

	private void nextToken() {
		try {
			lookAhead = lexer.yylex();
		}
		catch (IOException e) {
			ErrorManager.fatalError(e);
		}
		
	}
	
	private void match (LexicalClass expectedClass) {
		if (lookAhead.getLexClass() == expectedClass)
			nextToken();
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										expectedClass);
	}
	
	private void Programa () {
		if (lookAhead.getLexClass() == LexicalClass.BOOL || 
				lookAhead.getLexClass() == LexicalClass.NUM) {
			Sec_Declaracion();
			match(LexicalClass.SECTION_SEPARATOR);
			Sec_Instruccion();
			match(LexicalClass.EOF);
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.BOOL, LexicalClass.NUM);
	}

	private void Sec_Declaracion() {
		if (lookAhead.getLexClass() == LexicalClass.BOOL || 
				lookAhead.getLexClass() == LexicalClass.NUM) {
			Declaracion();
			Sec_Declaracion_p();
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.BOOL, LexicalClass.NUM);
		
	}

	private void Sec_Declaracion_p() {
		if (lookAhead.getLexClass() == LexicalClass.SEMICOLON) {
			match(LexicalClass.SEMICOLON);
			Sec_Declaracion();
		}
		else if (lookAhead.getLexClass() == LexicalClass.SECTION_SEPARATOR) {}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.SEMICOLON, 
										LexicalClass.SECTION_SEPARATOR);		
	}
	
	private void Declaracion() {
		if (lookAhead.getLexClass() == LexicalClass.BOOL || 
				lookAhead.getLexClass() == LexicalClass.NUM) {
			Tipo();
			match(LexicalClass.IDENTIFIER);
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.BOOL, LexicalClass.NUM);
		
	}

	private void Tipo() {
		if (lookAhead.getLexClass() == LexicalClass.BOOL) {
			match(LexicalClass.BOOL);
		}
		else if (lookAhead.getLexClass() == LexicalClass.NUM) {
			match(LexicalClass.NUM);
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.BOOL, LexicalClass.NUM);
	}
	
	private void Sec_Instruccion() {
		if (lookAhead.getLexClass() == LexicalClass.IDENTIFIER) {
			Instruccion();
			Sec_Instruccion_p();
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.IDENTIFIER);
		
	}

	private void Sec_Instruccion_p() {
		if (lookAhead.getLexClass() == LexicalClass.SEMICOLON) {
			match(LexicalClass.SEMICOLON);
			Sec_Instruccion();
		}
		else if (lookAhead.getLexClass() == LexicalClass.EOF) {}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.SEMICOLON, LexicalClass.EOF);
		
	}

	private void Instruccion() {
		if (lookAhead.getLexClass() == LexicalClass.IDENTIFIER) {
			match(LexicalClass.IDENTIFIER);
			match(LexicalClass.ASSIGNMENT);
			Exp();
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.IDENTIFIER);
		
	}

	private void Exp() {
		if (lookAhead.getLexClass() == LexicalClass.LITERAL_NUMBER || 
				lookAhead.getLexClass() == LexicalClass.IDENTIFIER ||
				lookAhead.getLexClass() == LexicalClass.OPEN_PARENTHESIS ||
				lookAhead.getLexClass() == LexicalClass.OP_SUB ||
				lookAhead.getLexClass() == LexicalClass.OP_NOT ||
				lookAhead.getLexClass() == LexicalClass.VALUE_TRUE ||
				lookAhead.getLexClass() == LexicalClass.VALUE_FALSE) {
			Exp0();
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.LITERAL_NUMBER, LexicalClass.IDENTIFIER, 
										LexicalClass.OPEN_PARENTHESIS, LexicalClass.OP_SUB, 
										LexicalClass.OP_NOT, LexicalClass.VALUE_TRUE, 
										LexicalClass.VALUE_FALSE);
	}

	private void Exp0() {
		if (lookAhead.getLexClass() == LexicalClass.LITERAL_NUMBER || 
				lookAhead.getLexClass() == LexicalClass.IDENTIFIER ||
				lookAhead.getLexClass() == LexicalClass.OPEN_PARENTHESIS ||
				lookAhead.getLexClass() == LexicalClass.OP_SUB ||
				lookAhead.getLexClass() == LexicalClass.OP_NOT ||
				lookAhead.getLexClass() == LexicalClass.VALUE_TRUE ||
				lookAhead.getLexClass() == LexicalClass.VALUE_FALSE) {
			Exp1();
			Exp0p();
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.LITERAL_NUMBER, LexicalClass.IDENTIFIER, 
										LexicalClass.OPEN_PARENTHESIS, LexicalClass.OP_SUB, 
										LexicalClass.OP_NOT, LexicalClass.VALUE_TRUE, 
										LexicalClass.VALUE_FALSE);
		
	}

	private void Exp0p() {
		if (lookAhead.getLexClass() == LexicalClass.OP_ADD ||
				lookAhead.getLexClass() == LexicalClass.OP_SUB) {
			Op0();
			Exp1();
			Exp0p();
		}
		else if (lookAhead.getLexClass() == LexicalClass.CLOSE_PARENTHESIS ||
					lookAhead.getLexClass() == LexicalClass.SEMICOLON ||
					lookAhead.getLexClass() == LexicalClass.EOF) {}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.OP_ADD, LexicalClass.OP_SUB, 
										LexicalClass.CLOSE_PARENTHESIS, LexicalClass.EOF,
										LexicalClass.SEMICOLON);
		
	}

	private void Op0() {
		if (lookAhead.getLexClass() == LexicalClass.OP_ADD) {
			match(LexicalClass.OP_ADD);
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_SUB) {
			match(LexicalClass.OP_SUB);
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.OP_ADD, LexicalClass.OP_SUB);
		
	}

	private void Exp1() {
		if (lookAhead.getLexClass() == LexicalClass.LITERAL_NUMBER || 
				lookAhead.getLexClass() == LexicalClass.IDENTIFIER ||
				lookAhead.getLexClass() == LexicalClass.OPEN_PARENTHESIS ||
				lookAhead.getLexClass() == LexicalClass.OP_SUB ||
				lookAhead.getLexClass() == LexicalClass.OP_NOT ||
				lookAhead.getLexClass() == LexicalClass.VALUE_TRUE ||
				lookAhead.getLexClass() == LexicalClass.VALUE_FALSE) {
			Exp2();
			Exp1p();
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.LITERAL_NUMBER, LexicalClass.IDENTIFIER, 
										LexicalClass.OPEN_PARENTHESIS, LexicalClass.OP_SUB, 
										LexicalClass.OP_NOT, LexicalClass.VALUE_TRUE, 
										LexicalClass.VALUE_FALSE);
		
	}

	private void Exp1p() {
		if (lookAhead.getLexClass() == LexicalClass.OP_AND) {
			match(LexicalClass.OP_AND);
			Exp1();
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_OR) {
			match(LexicalClass.OP_OR);
			Exp2();
		}
		else if (lookAhead.getLexClass() == LexicalClass.CLOSE_PARENTHESIS || 
				lookAhead.getLexClass() == LexicalClass.OP_ADD ||
				lookAhead.getLexClass() == LexicalClass.OP_SUB||
				lookAhead.getLexClass() == LexicalClass.SEMICOLON ||
				lookAhead.getLexClass() == LexicalClass.EOF) {}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.OP_AND, LexicalClass.OP_OR, 
										LexicalClass.CLOSE_PARENTHESIS, LexicalClass.OP_SUB, 
										LexicalClass.OP_ADD, LexicalClass.SEMICOLON, 
										LexicalClass.EOF);
		
	}

	private void Exp2() {
		if (lookAhead.getLexClass() == LexicalClass.LITERAL_NUMBER || 
				lookAhead.getLexClass() == LexicalClass.IDENTIFIER ||
				lookAhead.getLexClass() == LexicalClass.OPEN_PARENTHESIS ||
				lookAhead.getLexClass() == LexicalClass.OP_SUB ||
				lookAhead.getLexClass() == LexicalClass.OP_NOT ||
				lookAhead.getLexClass() == LexicalClass.VALUE_TRUE ||
				lookAhead.getLexClass() == LexicalClass.VALUE_FALSE) {
			Exp3();
			Exp2p();
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.LITERAL_NUMBER, LexicalClass.IDENTIFIER, 
										LexicalClass.OPEN_PARENTHESIS, LexicalClass.OP_SUB, 
										LexicalClass.OP_NOT, LexicalClass.VALUE_TRUE, 
										LexicalClass.VALUE_FALSE);
		
	}

	private void Exp2p() {
		if (lookAhead.getLexClass() == LexicalClass.OP_LESSER || 
				lookAhead.getLexClass() == LexicalClass.OP_LESSER_EQUAL||
				lookAhead.getLexClass() == LexicalClass.OP_GREATER_EQUAL ||
				lookAhead.getLexClass() == LexicalClass.OP_GREATER ||
				lookAhead.getLexClass() == LexicalClass.OP_EQUALS ||
				lookAhead.getLexClass() == LexicalClass.OP_DIFFERENT) {
			Op2();
			Exp3();
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_OR || 
				lookAhead.getLexClass() == LexicalClass.OP_AND||
				lookAhead.getLexClass() == LexicalClass.CLOSE_PARENTHESIS||
				lookAhead.getLexClass() == LexicalClass.OP_ADD ||
				lookAhead.getLexClass() == LexicalClass.OP_SUB ||
				lookAhead.getLexClass() == LexicalClass.SEMICOLON ||
				lookAhead.getLexClass() == LexicalClass.EOF) {} 
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.OP_LESSER, LexicalClass.OP_LESSER_EQUAL, 
										LexicalClass.OP_GREATER, LexicalClass.OP_GREATER_EQUAL, 
										LexicalClass.OP_EQUALS, LexicalClass.OP_DIFFERENT, 
										LexicalClass.OP_OR, LexicalClass.OP_AND,
										LexicalClass.CLOSE_PARENTHESIS, LexicalClass.OP_ADD,
										LexicalClass.OP_SUB, LexicalClass.SEMICOLON,
										LexicalClass.EOF);
		
	}

	private void Op2() {
		if (lookAhead.getLexClass() == LexicalClass.OP_LESSER) {
			match(LexicalClass.OP_LESSER);
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_LESSER_EQUAL) {
			match(LexicalClass.OP_LESSER_EQUAL);
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_GREATER_EQUAL) {
			match(LexicalClass.OP_GREATER_EQUAL);
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_GREATER) {
			match(LexicalClass.OP_GREATER);
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_EQUALS) {
			match(LexicalClass.OP_EQUALS);
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_DIFFERENT) {
			match(LexicalClass.OP_DIFFERENT);
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.OP_LESSER, LexicalClass.OP_LESSER_EQUAL, 
										LexicalClass.OP_GREATER, LexicalClass.OP_GREATER_EQUAL, 
										LexicalClass.OP_EQUALS, LexicalClass.OP_DIFFERENT);
		
	}

	private void Exp3() {
		if (lookAhead.getLexClass() == LexicalClass.LITERAL_NUMBER || 
				lookAhead.getLexClass() == LexicalClass.IDENTIFIER ||
				lookAhead.getLexClass() == LexicalClass.OPEN_PARENTHESIS ||
				lookAhead.getLexClass() == LexicalClass.OP_SUB ||
				lookAhead.getLexClass() == LexicalClass.OP_NOT ||
				lookAhead.getLexClass() == LexicalClass.VALUE_TRUE ||
				lookAhead.getLexClass() == LexicalClass.VALUE_FALSE) {
			Exp4();
			Exp3p();
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.LITERAL_NUMBER, LexicalClass.IDENTIFIER, 
										LexicalClass.OPEN_PARENTHESIS, LexicalClass.OP_SUB, 
										LexicalClass.OP_NOT, LexicalClass.VALUE_TRUE, 
										LexicalClass.VALUE_FALSE);
		
	}

	private void Exp3p() {
		if (lookAhead.getLexClass() == LexicalClass.OP_MUL ||
				lookAhead.getLexClass() == LexicalClass.OP_DIV) {
			Op3();
			Exp4();
			Exp3p();
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_AND ||
					lookAhead.getLexClass() == LexicalClass.OP_OR ||
					lookAhead.getLexClass() == LexicalClass.OP_LESSER ||
					lookAhead.getLexClass() == LexicalClass.OP_LESSER_EQUAL ||
					lookAhead.getLexClass() == LexicalClass.OP_GREATER ||
					lookAhead.getLexClass() == LexicalClass.OP_GREATER_EQUAL ||
					lookAhead.getLexClass() == LexicalClass.OP_EQUALS ||
					lookAhead.getLexClass() == LexicalClass.OP_DIFFERENT ||
					lookAhead.getLexClass() == LexicalClass.CLOSE_PARENTHESIS ||
					lookAhead.getLexClass() == LexicalClass.OP_ADD ||
					lookAhead.getLexClass() == LexicalClass.OP_SUB ||
					lookAhead.getLexClass() == LexicalClass.SEMICOLON ||
					lookAhead.getLexClass() == LexicalClass.EOF) {}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.OP_MUL, LexicalClass.OP_DIV, 
										LexicalClass.OP_AND, LexicalClass.OP_OR,
										LexicalClass.OP_LESSER, LexicalClass.OP_LESSER_EQUAL,
										LexicalClass.OP_GREATER, LexicalClass.OP_GREATER_EQUAL,
										LexicalClass.OP_EQUALS, LexicalClass.OP_DIFFERENT,
										LexicalClass.CLOSE_PARENTHESIS, LexicalClass.OP_ADD,
										LexicalClass.OP_SUB, LexicalClass.SEMICOLON,
										LexicalClass.EOF);
		
	}

	private void Op3() {
		if (lookAhead.getLexClass() == LexicalClass.OP_MUL) {
			match(LexicalClass.OP_MUL);
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_DIV) {
			match(LexicalClass.OP_DIV);
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.OP_MUL, LexicalClass.OP_DIV);
		
	}

	private void Exp4() {
		if (lookAhead.getLexClass() == LexicalClass.OP_SUB) {
			match(LexicalClass.OP_SUB);
			Exp4();
		}
		else if (lookAhead.getLexClass() == LexicalClass.OP_NOT) {
			match(LexicalClass.OP_NOT);
			Exp5();
		}
		else if (lookAhead.getLexClass() == LexicalClass.LITERAL_NUMBER ||
					lookAhead.getLexClass() == LexicalClass.IDENTIFIER ||
					lookAhead.getLexClass() == LexicalClass.VALUE_TRUE||
					lookAhead.getLexClass() == LexicalClass.VALUE_FALSE||
					lookAhead.getLexClass() == LexicalClass.OPEN_PARENTHESIS) {
			Exp5();
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.OP_SUB, LexicalClass.OP_NOT,
										LexicalClass.LITERAL_NUMBER, LexicalClass.IDENTIFIER,
										LexicalClass.VALUE_TRUE, LexicalClass.VALUE_FALSE,
										LexicalClass.OPEN_PARENTHESIS);
		
	}

	private void Exp5() {
		if (lookAhead.getLexClass() == LexicalClass.LITERAL_NUMBER ||
				lookAhead.getLexClass() == LexicalClass.IDENTIFIER ||
				lookAhead.getLexClass() == LexicalClass.VALUE_TRUE||
				lookAhead.getLexClass() == LexicalClass.VALUE_FALSE) {
			Lit();
		}
		else if (lookAhead.getLexClass() == LexicalClass.OPEN_PARENTHESIS) {
			match(LexicalClass.OPEN_PARENTHESIS);
			Exp0();
			match(LexicalClass.CLOSE_PARENTHESIS);
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.LITERAL_NUMBER, LexicalClass.IDENTIFIER,
										LexicalClass.VALUE_TRUE, LexicalClass.VALUE_FALSE,
										LexicalClass.OPEN_PARENTHESIS);
		
	}

	private void Lit() {
		if (lookAhead.getLexClass() == LexicalClass.IDENTIFIER) {
			match(LexicalClass.IDENTIFIER);
		}
		else if (lookAhead.getLexClass() == LexicalClass.LITERAL_NUMBER) {
			match(LexicalClass.LITERAL_NUMBER);
		}
		else if (lookAhead.getLexClass() == LexicalClass.VALUE_TRUE) {
			match(LexicalClass.VALUE_TRUE);
		}
		else if (lookAhead.getLexClass() == LexicalClass.VALUE_FALSE) {
			match(LexicalClass.VALUE_FALSE);
		}
		else
			ErrorManager.syntaxError(lookAhead.getRow(), lookAhead.getLexClass(), 
										LexicalClass.LITERAL_NUMBER, LexicalClass.IDENTIFIER,
										LexicalClass.VALUE_TRUE, LexicalClass.VALUE_FALSE);
		
	}
	
	
	
	
	
}
