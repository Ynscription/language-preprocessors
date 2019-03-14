import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import syntactical.SyntaxAnalyzer;

public class Main {

	public static void main(String[] args) {
		try {
			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			SyntaxAnalyzer analyzer = new SyntaxAnalyzer(input);
			analyzer.run();
		} 
		catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

}
