package lexical;


class Lexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

  private LexerOps ops;
  public String lexeme() {return yytext();}
  public int row() {return yyline+1;}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Lexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Lexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

		ops = new LexerOps(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NOT_ACCEPT,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NOT_ACCEPT,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"32:8,1:3,32:2,1,32:18,1,11,32:4,9,32,7,8,16,2,32,6,4,15,3:10,32,10,14,12,13" +
",32:2,30:4,5,30:21,32:4,31,32,23,20,30,24,27,28,30:5,22,19,17,21,30:2,25,29" +
",26,18,30:5,32:65413,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,51,
"0,1:2,2,3,1,4,2,1:3,5,6,7,1:7,4:7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,1" +
"3,22,23,24,25,26,27,28,29")[0];

	private int yy_nxt[][] = unpackFromString(30,33,
"1,2,3,4,5,6,7,8,9,30,10,34,11,12,13,14,15,41,6:2,47,31,6,43,6:2,48,6,50,6:2" +
",5:2,-1:36,4,-1:32,4,28,32,-1:21,32,-1:8,6,-1,6,-1:11,6:15,-1:13,18,-1:32,1" +
"9,-1:32,20,-1:23,29,-1:32,29,-1,32,-1:21,32,-1:14,16,-1:26,6,-1,6,-1:11,6:8" +
",21,6:6,-1:3,42,33,-1:2,42,-1:29,33,-1:41,17,-1:23,6,-1,6,-1:11,6:2,22,6:12" +
",-1:4,6,-1,6,-1:11,6:9,23,6:5,-1:4,6,-1,6,-1:11,6:7,24,6:7,-1:4,6,-1,6,-1:1" +
"1,6:5,25,6:9,-1:4,6,-1,6,-1:11,6:10,26,6:4,-1:4,6,-1,6,-1:11,6:10,27,6:4,-1" +
":4,6,-1,6,-1:11,6,35,6:2,36,6:10,-1:4,6,-1,6,-1:11,37,6:14,-1:4,6,-1,6,-1:1" +
"1,6:4,38,6:10,-1:4,6,-1,6,-1:11,6,39,6:13,-1:4,6,-1,6,-1:11,6:12,40,6:2,-1:" +
"4,6,-1,6,-1:11,6:4,44,6:10,-1:4,6,-1,6,-1:11,6:8,45,6:6,-1:4,6,-1,6,-1:11,6" +
":5,46,6:9,-1:4,6,-1,6,-1:11,6:6,49,6:8,-1");

	public LexicalUnit yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

				return ops.unitEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{}
					case -3:
						break;
					case 3:
						{return ops.unitMas();}
					case -4:
						break;
					case 4:
						{return ops.unitNumber();}
					case -5:
						break;
					case 5:
						{ops.error();}
					case -6:
						break;
					case 6:
						{return ops.unitID();}
					case -7:
						break;
					case 7:
						{return ops.unitMenos();}
					case -8:
						break;
					case 8:
						{return ops.unitAbrePar();}
					case -9:
						break;
					case 9:
						{return ops.unitCierraPar();}
					case -10:
						break;
					case 10:
						{return ops.unitSepLin();}
					case -11:
						break;
					case 11:
						{return ops.unitAsignacion();}
					case -12:
						break;
					case 12:
						{return ops.unitMayor();}
					case -13:
						break;
					case 13:
						{return ops.unitMenor();}
					case -14:
						break;
					case 14:
						{return ops.unitDiv();}
					case -15:
						break;
					case 15:
						{return ops.unitPor();}
					case -16:
						break;
					case 16:
						{return ops.unitSepSec();}
					case -17:
						break;
					case 17:
						{return ops.unitDistinto();}
					case -18:
						break;
					case 18:
						{return ops.unitIgual();}
					case -19:
						break;
					case 19:
						{return ops.unitMayorIgual();}
					case -20:
						break;
					case 20:
						{return ops.unitMenorIgual();}
					case -21:
						break;
					case 21:
						{return ops.unitOr();}
					case -22:
						break;
					case 22:
						{return ops.unitNum();}
					case -23:
						break;
					case 23:
						{return ops.unitNot();}
					case -24:
						break;
					case 24:
						{return ops.unitAnd();}
					case -25:
						break;
					case 25:
						{return ops.unitBool();}
					case -26:
						break;
					case 26:
						{return ops.unitTrue();}
					case -27:
						break;
					case 27:
						{return ops.unitFalse();}
					case -28:
						break;
					case 29:
						{return ops.unitNumber();}
					case -29:
						break;
					case 30:
						{ops.error();}
					case -30:
						break;
					case 31:
						{return ops.unitID();}
					case -31:
						break;
					case 33:
						{return ops.unitNumber();}
					case -32:
						break;
					case 34:
						{ops.error();}
					case -33:
						break;
					case 35:
						{return ops.unitID();}
					case -34:
						break;
					case 36:
						{return ops.unitID();}
					case -35:
						break;
					case 37:
						{return ops.unitID();}
					case -36:
						break;
					case 38:
						{return ops.unitID();}
					case -37:
						break;
					case 39:
						{return ops.unitID();}
					case -38:
						break;
					case 40:
						{return ops.unitID();}
					case -39:
						break;
					case 41:
						{return ops.unitID();}
					case -40:
						break;
					case 43:
						{return ops.unitID();}
					case -41:
						break;
					case 44:
						{return ops.unitID();}
					case -42:
						break;
					case 45:
						{return ops.unitID();}
					case -43:
						break;
					case 46:
						{return ops.unitID();}
					case -44:
						break;
					case 47:
						{return ops.unitID();}
					case -45:
						break;
					case 48:
						{return ops.unitID();}
					case -46:
						break;
					case 49:
						{return ops.unitID();}
					case -47:
						break;
					case 50:
						{return ops.unitID();}
					case -48:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
	private LexerOps unitID() {
		// TODO Auto-generated method stub
		return null;
	}
}
