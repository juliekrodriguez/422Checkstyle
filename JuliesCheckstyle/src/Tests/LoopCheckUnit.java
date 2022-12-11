package Tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

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

import checkstylePro.LoopingCheck;

class LoopCheckUnit 
{

	public LoopingCheck um = new LoopingCheck();
	public int[] uc =  new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE };
	
	@Test
	public void testgetAcceptableTokens() 
	{
		Assert.assertArrayEquals(uc,um.getAcceptableTokens());
	}

	@Test
	public void testgetRequiredTokens() 
	{
		int[] uc =  new int[0];
		Assert.assertArrayEquals(uc,um.getRequiredTokens());
	}
	
	@Test
	public void testgetDefaultTokens() 
	{
		Assert.assertArrayEquals(uc,um.getDefaultTokens());
	}
	
	@Test
	public void testBlackBoxLoops() throws IOException, CheckstyleException
	{
		LoopingCheck lc;
		lc = firstCheckBuilder();
		int loopCount = lc.getLoopingCount();
		
		assertTrue(loopCount == 4);	
	}
	
	private void help(AbstractCheck check, DetailAST ast) 
	{
        while (ast != null ) {
            check.visitToken(ast);
            help(check, ast.getFirstChild());
            ast = ast.getNextSibling();
        }
    }
	
	private LoopingCheck firstCheckBuilder() throws IOException, CheckstyleException 
	{
		//Deal with file paths
		String filePath = System.getProperty("user.dir");
		File file = new File(filePath + "/src/Tests/RandCheck.java");
		FileText fileTxt = new FileText(file, "UTF-8");
		FileContents fileCont = new FileContents(fileTxt);
		
		DetailAST root = JavaParser.parse(fileCont);
		
		LoopingCheck lc = new LoopingCheck();
		
		lc.configure(new DefaultConfiguration("Local"));
		lc.contextualize(new DefaultContext());
		
		lc.beginTree(root);
		help(lc, root);
		lc.finishTree(root);
		
		return lc;
	}
	
	
}
