package Tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Assert;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import checkstylePro.FirstChecker;

class FirstCheckUnit 
{

	public FirstChecker M = new FirstChecker();
	
	int[] ET =  new int[] 
	        {
			TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, 
			TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
			TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN,TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN,
			TokenTypes.EQUAL, TokenTypes.INC, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR,
			TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
			TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
			TokenTypes.POST_INC, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN,
			TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,
			TokenTypes.EXPR, TokenTypes.METHOD_CALL, TokenTypes.RCURLY, TokenTypes.CTOR_DEF,
			TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE,
			TokenTypes.LITERAL_DO, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH,
			TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION
	};
	
	@Test
	public void testgetAcceptableTokens() 
	{		
		Assert.assertArrayEquals(ET, M.getAcceptableTokens());
	}

	@Test
	public void testgetRequiredTokens() 
	{
		int [] ER = new int[] {
				TokenTypes.CTOR_DEF,
				TokenTypes.METHOD_DEF,
				TokenTypes.INSTANCE_INIT,
				TokenTypes.STATIC_INIT,
		};
		Assert.assertArrayEquals(ER, M.getRequiredTokens());
	}
	
	@Test
	public void testgetDefaultTokens() 
	{
		Assert.assertArrayEquals(ET, M.getDefaultTokens());
	}
	
	@Test
	public void testBlackBoxOperators() throws IOException, CheckstyleException
	{
		FirstChecker mc;
		mc = firstCheckBuilder();
		
		Map<String, Double> results = mc.getResults();
		
		assertTrue(results.get("operators") == (double)8);
	}
	
	@Test
	public void testBlackBoxEffort() throws IOException, CheckstyleException
	{
		FirstChecker mc;
		mc = firstCheckBuilder();
		Map<String, Double> results = mc.getResults();
		assertTrue(results.get("effort") == 196.64);
	}
	
	@Test
	public void testBlackBoxDifficulty() throws IOException, CheckstyleException
	{
		FirstChecker mc;
		mc = firstCheckBuilder();
		Map<String, Double> results = mc.getResults();
		
		assertTrue(results.get("difficulty") == 3.333);
	}
	
	@Test
	public void testBlackBoxVolume() throws IOException, CheckstyleException
	{
		FirstChecker mc;
		mc = firstCheckBuilder();
		Map<String, Double> results = mc.getResults();
		
		assertTrue(results.get("volume") == results.get("volume"));
	}
	
	@Test
	public void testBlackBoxOperands() throws IOException, CheckstyleException
	{
		FirstChecker mc;
		mc = firstCheckBuilder();
		Map<String, Double> results = mc.getResults();
		assertTrue(results.get("operands") == 15);
	}
	
	@Test
	public void testBlackBoxLength() throws IOException, CheckstyleException
	{
		FirstChecker mc;
		mc = firstCheckBuilder();
		Map<String, Double> results = mc.getResults();
		assertTrue(results.get("length") == 23);
	}
	
	@Test
	public void testBlackBoxVocab() throws IOException, CheckstyleException
	{
		FirstChecker mc;
		mc = firstCheckBuilder();
		Map<String, Double> results = mc.getResults();
		assertTrue(results.get("vocab") == 13);	
	}
	private void help(AbstractCheck check, DetailAST ast) 
	{
        while (ast != null ) {
            check.visitToken(ast);
            help(check, ast.getFirstChild());
            ast = ast.getNextSibling();
        }
    }
	
	private FirstChecker firstCheckBuilder() throws IOException, CheckstyleException 
	{
		String filePath = System.getProperty("user.dir");
		File file = new File(filePath + "/src/Tests/ExampleCode.java");
		FileText fileTxt = new FileText(file, "UTF-8");
		FileContents fileCont = new FileContents(fileTxt);
		DetailAST root = JavaParser.parse(fileCont);
		FirstChecker mc = new FirstChecker();
		
		mc.configure(new DefaultConfiguration("Local"));
		mc.contextualize(new DefaultContext());
		
		mc.beginTree(root);
		help(mc, root);
		mc.finishTree(root);
		
		return mc;
	}
}
