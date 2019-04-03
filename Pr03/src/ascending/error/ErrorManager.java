package ascending.error;


import ascending.lexical.LexicalClass;
import ascending.lexical.LexicalUnit;

public abstract class ErrorManager {
	
	public static void lexicalError (int row, String lexeme) {
		System.err.println("Lexical error in row " + row + ": Unexpecter character: " + lexeme);
		System.exit(1);
	}

	public static void fatalError(Exception e) {
		System.err.println(e);
		e.printStackTrace(System.err);
		System.exit(1);
		
	}

	public static void syntaxError(int row, LexicalClass foundClass, LexicalClass ... expectedClass) {
		String error = "Syntax error in row " + row + ":" + System.lineSeparator() + 
							"Found: " + foundClass + System.lineSeparator() +
							"Expected: ";
		for (LexicalClass expected : expectedClass)
			error += expected + " ";
		System.err.println(error);
		
		
	}
	
	public static void syntaxError(LexicalUnit lex_unit) {
		String error = "Syntax error in row " + lex_unit.getRow() + ":" + System.lineSeparator() + 
							"Found: " + lex_unit.getLexClass() + System.lineSeparator() +
							"Expected: ?";
		System.err.println(error);
		
		
	}
}
