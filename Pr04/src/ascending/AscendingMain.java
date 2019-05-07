package ascending;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import abstractSyntax.Program;
import ascending.eval.Eval;
import ascending.lexical.Lexer;

public class AscendingMain {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			Lexer lexer = new Lexer(input);
			Eval sAnalizer = new Eval(lexer);
			Program p = (Program) sAnalizer.parse().value;
			System.out.println("-------------------");
			System.out.println(p);
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
