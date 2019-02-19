import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import lexer.Lexer;
import lexer.LexicalClass;
import lexer.LexicalUnit;

public class Main {

	public static void main(String[] args) {
		try {
			InputStreamReader input = new InputStreamReader(new FileInputStream(args[0]));
			Lexer lexer = new Lexer(input);
			LexicalUnit lu;
			do {
				lu = lexer.nextToken();
				if (lu != null)
					System.out.println(lu);				
			}while (lu != null && lu.getLexClass() != LexicalClass.EOF);
			if (lu == null)
				System.err.println("Unexpecter character at " + lexer.getCurrRow() + ":" + lexer.getCurrColumn());
					
				
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

}
