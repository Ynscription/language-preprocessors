package DescendingParser;
import java.io.FileReader;
public class Main{
   public static void main(String[] args) throws Exception {
      Parser parser = new Parser(new FileReader(args[0]));
	  parser.Programa();
   }
}
