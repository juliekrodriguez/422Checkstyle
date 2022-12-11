package checkstylePro;

import com.puppycrawl.tools.checkstyle.api.*;


public class LoopingCheck extends AbstractCheck{
	  private int count = 0;
	  
	  private int FLC = 0;
	  
	  @Override
	  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE };
	  }

	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }

	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE };
	  }
	  
	  public int getLoopingCount() {
          return FLC;
      }

	  @Override
	  public void visitToken(DetailAST ast) {
		  count++;
	  }
	  
	  @Override
	  public void finishTree(DetailAST ast) {
		  log(ast.getLineNo(), "loopcount", count);
		  
		  FLC = count;
		  
		  count = 0;
	  }
	  
}

