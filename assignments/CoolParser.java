// Generated from /home/don33baller/ECS652U-cw-distroSeman/ECS652U-cw-group11/src/CoolParser.g4 by ANTLR 4.6
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PERIOD=1, COMMA=2, AT=3, SEMICOLON=4, COLON=5, CURLY_OPEN=6, CURLY_CLOSE=7, 
		PARENT_OPEN=8, PARENT_CLOSE=9, PLUS_OPERATOR=10, MINUS_OPERATOR=11, MULT_OPERATOR=12, 
		DIV_OPERATOR=13, INT_COMPLEMENT_OPERATOR=14, LESS_OPERATOR=15, LESS_EQ_OPERATOR=16, 
		EQ_OPERATOR=17, ASSIGN_OPERATOR=18, RIGHTARROW=19, ERROR=20;
	public static final int
		RULE_program = 0, RULE_coolClass = 1;
	public static final String[] ruleNames = {
		"program", "coolClass"
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

	@Override
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CoolParser.EOF, 0); }
		public List<CoolClassContext> coolClass() {
			return getRuleContexts(CoolClassContext.class);
		}
		public CoolClassContext coolClass(int i) {
			return getRuleContext(CoolClassContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(4);
				coolClass();
				setState(5);
				match(SEMICOLON);
				}
				}
				setState(9); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SEMICOLON );
			setState(11);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CoolClassContext extends ParserRuleContext {
		public CoolClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coolClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCoolClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCoolClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCoolClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CoolClassContext coolClass() throws RecognitionException {
		CoolClassContext _localctx = new CoolClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_coolClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\26\22\4\2\t\2\4\3"+
		"\t\3\3\2\3\2\3\2\6\2\n\n\2\r\2\16\2\13\3\2\3\2\3\3\3\3\3\3\2\2\4\2\4\2"+
		"\2\20\2\t\3\2\2\2\4\17\3\2\2\2\6\7\5\4\3\2\7\b\7\6\2\2\b\n\3\2\2\2\t\6"+
		"\3\2\2\2\n\13\3\2\2\2\13\t\3\2\2\2\13\f\3\2\2\2\f\r\3\2\2\2\r\16\7\2\2"+
		"\3\16\3\3\2\2\2\17\20\3\2\2\2\20\5\3\2\2\2\3\13";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}