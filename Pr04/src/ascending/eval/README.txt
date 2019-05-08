Generar el parser con JLex:
	· Navegar a Practicas\Pr04\src\ascending\lexical y hacer
		java -jar JLex.jar lexer.l
	· Renombrar el archivo lexer.l.java a Lexer.java

Generar el analizador sintáctico con CUP:
	· Navegar a Practicas\Pr04\src\ascending\syntactical y hacer
		java -jar cup.jar Eval.cup
	· Mover el archivo sym.java a la carpeta Practicas\Pr04\src\ascending\lexical

Configurar el proyecto en eclipse:
	· Añadir librería cup como jar externo (usar el archivo cup.jar que se encuentra en Practicas\Pr04\src\ascending\syntactical)

	· Abrir el archivo sym.java y cambiar el paquete de ascending.syntactical a ascending.lexical
	· Renombrar la clase de sym a LexicalClass

	· Abrir el archivo parser.java y editar el código renombrando la clase de parser a Eval
