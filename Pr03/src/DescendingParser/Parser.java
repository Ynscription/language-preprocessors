/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
package DescendingParser;

public class Parser implements ParserConstants {

  final public void Programa() throws ParseException {
    Sec_Declaracion();
    jj_consume_token(16);
    Sec_Instruccion();
    jj_consume_token(0);
  }

  final public void Sec_Declaracion() throws ParseException {
    Declaracion();
    Sec_Declaracion_p();
  }

  final public void Sec_Declaracion_p() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 17:{
      jj_consume_token(17);
      Sec_Declaracion();
      break;
      }
    default:
      jj_la1[0] = jj_gen;

    }
  }

  final public void Declaracion() throws ParseException {
    Tipo();
    jj_consume_token(ID);
  }

  final public void Tipo() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case num:{
      jj_consume_token(num);
      break;
      }
    case bool:{
      jj_consume_token(bool);
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Sec_Instruccion() throws ParseException {
    Instruccion();
    Sec_Instruccion_p();
  }

  final public void Sec_Instruccion_p() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 17:{
      jj_consume_token(17);
      Sec_Instruccion();
      break;
      }
    default:
      jj_la1[2] = jj_gen;

    }
  }

  final public void Instruccion() throws ParseException {
    jj_consume_token(ID);
    jj_consume_token(18);
    Exp();
  }

  final public void Exp() throws ParseException {
    Exp0();
  }

  final public void Exp0() throws ParseException {
    Exp1();
    Exp0_p();
  }

  final public void Exp0_p() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 19:
    case 20:{
      Op0();
      Exp1();
      Exp0_p();
      break;
      }
    default:
      jj_la1[3] = jj_gen;

    }
  }

  final public void Op0() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 19:{
      jj_consume_token(19);
      break;
      }
    case 20:{
      jj_consume_token(20);
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Exp1() throws ParseException {
    Exp2();
    Exp1_p();
  }

  final public void Exp1_p() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case and:{
      jj_consume_token(and);
      Exp1();
      break;
      }
    case or:{
      jj_consume_token(or);
      Exp2();
      break;
      }
    default:
      jj_la1[5] = jj_gen;

    }
  }

  final public void Exp2() throws ParseException {
    Exp3();
    Exp2_p();
  }

  final public void Exp2_p() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:{
      Op2();
      Exp3();
      break;
      }
    default:
      jj_la1[6] = jj_gen;

    }
  }

  final public void Op2() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 21:{
      jj_consume_token(21);
      break;
      }
    case 22:{
      jj_consume_token(22);
      break;
      }
    case 23:{
      jj_consume_token(23);
      break;
      }
    case 24:{
      jj_consume_token(24);
      break;
      }
    case 25:{
      jj_consume_token(25);
      break;
      }
    case 26:{
      jj_consume_token(26);
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Exp3() throws ParseException {
    Exp4();
    Exp3_p();
  }

  final public void Exp3_p() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 27:
    case 28:{
      Op3();
      Exp4();
      Exp3_p();
      break;
      }
    default:
      jj_la1[8] = jj_gen;

    }
  }

  final public void Op3() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 27:{
      jj_consume_token(27);
      break;
      }
    case 28:{
      jj_consume_token(28);
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Exp4() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 20:{
      jj_consume_token(20);
      Exp4();
      break;
      }
    case not:{
      jj_consume_token(not);
      Exp5();
      break;
      }
    case lit_n:
    case v_true:
    case v_false:
    case ID:
    case 29:{
      Exp5();
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Exp5() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case lit_n:
    case v_true:
    case v_false:
    case ID:{
      Lit();
      break;
      }
    case 29:{
      jj_consume_token(29);
      Exp0();
      jj_consume_token(30);
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Lit() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ID:{
      jj_consume_token(ID);
      break;
      }
    case v_true:{
      jj_consume_token(v_true);
      break;
      }
    case v_false:{
      jj_consume_token(v_false);
      break;
      }
    case lit_n:{
      jj_consume_token(lit_n);
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[13];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x20000,0x6,0x20000,0x180000,0x180000,0x600,0x7e00000,0x7e00000,0x18000000,0x18000000,0x20107900,0x20007100,0x7100,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      System.out.println(token);
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[31];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 13; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 31; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

                     }
