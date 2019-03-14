package error;

import java.io.IOException;

import lexical.LexicalClass;

public abstract class ErrorManager {
	
	public static void lexicalError (int row, String lexeme) {
		
	}

	public static void fatalError(Exception e) {
		System.err.println(e);
		e.printStackTrace(System.err);
		System.exit(1);
		
	}

	public static void syntaxError(int row, LexicalClass lexClass, LexicalClass ... expectedClass) {
		
		
	}
}
