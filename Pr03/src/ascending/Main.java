package ascending;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import ascending.lexical.Lexer;
import ascending.syntactical.SyntaxAnalyzer;

public class Main {

	public static void main(String[] args) {
		try {
			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			Lexer lexer = new Lexer(input);
			SyntaxAnalyzer sAnalizer = new SyntaxAnalyzer(lexer);
			sAnalizer.parse();
		} 
		catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		

	}

}
