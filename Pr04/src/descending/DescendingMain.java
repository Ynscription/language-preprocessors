package descending;

import java.io.FileNotFoundException;
import java.io.FileReader;

import abstractSyntax.Program;


public class DescendingMain {

	public static void main(String[] args) {
		try {
			Eval parser = new Eval(new FileReader(args[0]));
			Program p = parser.Prog();
			System.out.println(p);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

	}

}
