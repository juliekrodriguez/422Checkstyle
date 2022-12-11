package checkstylePro;

import com.puppycrawl.tools.checkstyle.api.*;
 
public class Checkstyle extends AbstractCheck {
	

	  private int counter = 0;
	  
	  @Override
      public void visitToken(DetailAST ast) {
          if (ast.branchContains(TokenTypes.TYPE))
          {
            counter++;  
          }
      }
	  
	  @Override
	  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.VARIABLE_DEF };
	  }

	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }

	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.VARIABLE_DEF };
	  }
	  
	  @Override
	  public void finishTree(DetailAST ast) {
		  log(ast.getLineNo(), "varcounter", counter);
		  counter = 0;
	  }
	}


