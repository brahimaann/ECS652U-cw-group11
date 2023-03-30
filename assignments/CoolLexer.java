// Generated from /home/don33baller/ECS652U-cw-distroSeman/ECS652U-cw-group11/src/CoolLexer.g4 by ANTLR 4.6
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PERIOD=1, COMMA=2, AT=3, SEMICOLON=4, COLON=5, CURLY_OPEN=6, CURLY_CLOSE=7, 
		PARENT_OPEN=8, PARENT_CLOSE=9, PLUS_OPERATOR=10, MINUS_OPERATOR=11, MULT_OPERATOR=12, 
		DIV_OPERATOR=13, INT_COMPLEMENT_OPERATOR=14, LESS_OPERATOR=15, LESS_EQ_OPERATOR=16, 
		EQ_OPERATOR=17, ASSIGN_OPERATOR=18, RIGHTARROW=19, ERROR=20;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PERIOD", "COMMA", "AT", "SEMICOLON", "COLON", "CURLY_OPEN", "CURLY_CLOSE", 
		"PARENT_OPEN", "PARENT_CLOSE", "PLUS_OPERATOR", "MINUS_OPERATOR", "MULT_OPERATOR", 
		"DIV_OPERATOR", "INT_COMPLEMENT_OPERATOR", "LESS_OPERATOR", "LESS_EQ_OPERATOR", 
		"EQ_OPERATOR", "ASSIGN_OPERATOR", "RIGHTARROW", "ERROR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "','", "'@'", "';'", "':'", "'{'", "'}'", "'('", "')'", "'+'", 
		"'-'", "'*'", "'/'", "'~'", "'<'", "'<='", "'='", "'<-'", "'=>'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PERIOD", "COMMA", "AT", "SEMICOLON", "COLON", "CURLY_OPEN", "CURLY_CLOSE", 
		"PARENT_OPEN", "PARENT_CLOSE", "PLUS_OPERATOR", "MINUS_OPERATOR", "MULT_OPERATOR", 
		"DIV_OPERATOR", "INT_COMPLEMENT_OPERATOR", "LESS_OPERATOR", "LESS_EQ_OPERATOR", 
		"EQ_OPERATOR", "ASSIGN_OPERATOR", "RIGHTARROW", "ERROR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\26V\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26\3\2\2U\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\61\3\2\2\2\13"+
		"\63\3\2\2\2\r\65\3\2\2\2\17\67\3\2\2\2\219\3\2\2\2\23;\3\2\2\2\25=\3\2"+
		"\2\2\27?\3\2\2\2\31A\3\2\2\2\33C\3\2\2\2\35E\3\2\2\2\37G\3\2\2\2!I\3\2"+
		"\2\2#L\3\2\2\2%N\3\2\2\2\'Q\3\2\2\2)T\3\2\2\2+,\7\60\2\2,\4\3\2\2\2-."+
		"\7.\2\2.\6\3\2\2\2/\60\7B\2\2\60\b\3\2\2\2\61\62\7=\2\2\62\n\3\2\2\2\63"+
		"\64\7<\2\2\64\f\3\2\2\2\65\66\7}\2\2\66\16\3\2\2\2\678\7\177\2\28\20\3"+
		"\2\2\29:\7*\2\2:\22\3\2\2\2;<\7+\2\2<\24\3\2\2\2=>\7-\2\2>\26\3\2\2\2"+
		"?@\7/\2\2@\30\3\2\2\2AB\7,\2\2B\32\3\2\2\2CD\7\61\2\2D\34\3\2\2\2EF\7"+
		"\u0080\2\2F\36\3\2\2\2GH\7>\2\2H \3\2\2\2IJ\7>\2\2JK\7?\2\2K\"\3\2\2\2"+
		"LM\7?\2\2M$\3\2\2\2NO\7>\2\2OP\7/\2\2P&\3\2\2\2QR\7?\2\2RS\7@\2\2S(\3"+
		"\2\2\2TU\13\2\2\2U*\3\2\2\2\3\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}