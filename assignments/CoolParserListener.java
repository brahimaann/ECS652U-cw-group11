// Generated from /home/don33baller/ECS652U-cw-distroSeman/ECS652U-cw-group11/src/CoolParser.g4 by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoolParser}.
 */
public interface CoolParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CoolParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#coolClass}.
	 * @param ctx the parse tree
	 */
	void enterCoolClass(CoolParser.CoolClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#coolClass}.
	 * @param ctx the parse tree
	 */
	void exitCoolClass(CoolParser.CoolClassContext ctx);
}