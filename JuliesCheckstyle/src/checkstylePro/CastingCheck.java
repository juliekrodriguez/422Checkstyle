package checkstylePro;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CastingCheck extends AbstractCheck{
	  private int count = 0;
	  
	  @Override
	  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.TYPECAST};
	  }

	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }

	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.TYPECAST};
	  }

	  @Override
	  public void visitToken(DetailAST ast) {
		  count++;
	  }
	  
	  @Override
	  public void finishTree(DetailAST ast) {
		  log(ast.getLineNo(), "castcount", count);
		  count = 0;
	  }
}

