Generar el parser con JLex:
	· Navegar a Practicas\Pr03\src\ascending\lexical y hacer
		java -jar JLex.jar lexer.l
	· Renombrar el archivo lexer.l.java a Lexer.java

Generar el analizador sintáctico con CUP:
	· Navegar a Practicas\Pr03\src\ascending\syntactical y hacer
		java -jar cup.jar syntaxAnalyzer.cup
	· Renombrar el archivo parser.java a SyntaxAnalyzer.java
	· Renombrar el archivo sym.java a LexicalClass.java
	· Mover el archivo LexicalClass.java a la carpeta Practicas\Pr03\src\ascending\lexical

Configurar el proyecto en eclipse:
	· Añadir librería cup como jar externo (usar el archivo cup.jar que se encuentra en Practicas\Pr03\src\ascending\syntactical)

	· Abrir el archivo LexicalClass.java y cambiar el paquete de ascending.syntactical a ascending.lexical
	· Renombrar la clase de sym a LexicalClass

	· Abrir el archivo SyntaxAnalyzer.java y editar el código renombrando la clase de parser a SyntaxAnalyzer
	· Añadir los siguientes imports a la clase SyntaxAnalyzer:
		import Symbol (java.cup.runtime)
		import LexicalUnit (ascending.lexical)
	· Comentar las líneas 261 y 262 de la clase SyntaxAnalyzer
		Estas líneas asignan un valor a variables locales que no se usan y provocan errores de casteo.
