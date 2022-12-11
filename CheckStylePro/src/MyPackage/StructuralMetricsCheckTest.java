package MyPackage;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class StructuralMetricsCheckTest {
	
	int[] EA = { 
			TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF, TokenTypes.METHOD_DEF, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_DO, TokenTypes.DO_WHILE,
			TokenTypes.LITERAL_WHILE, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR,
			TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON,
			TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE,
			TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF,
			TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
			TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC,
			TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,
			TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.EXPR,
			TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
			TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL };
	
	@Test
    public void testGetDefaultTokens() 
	{
        final StructuralMetricsCheck checkObj = new StructuralMetricsCheck();
        assertArrayEquals("Default required tokens are invalid",
            EA, checkObj.getDefaultTokens());
    }
	
	@Test
    public void testAcceptableTokens() 
	{
        final StructuralMetricsCheck checkObj = new StructuralMetricsCheck();
        final int[] expected = checkObj.getDefaultTokens();
        assertArrayEquals("Default required tokens are invalid",
            expected, checkObj.getAcceptableTokens());
    }
	
	
	@Test
	public void testFinishTree() throws Exception
	{
		final StructuralMetricsCheck checkObj = new StructuralMetricsCheck();
		//final DetailAST testAST = new DetailAST();
		//checkObj.finishTree(testAST);
	}
	
	@Test
	public void testVisitToken() throws Exception
	{
		final StructuralMetricsCheck checkObj = new StructuralMetricsCheck();
		//final DetailAST testAST = new DetailAST();
		//checkObj.beginTree(testAST);
	}
}
