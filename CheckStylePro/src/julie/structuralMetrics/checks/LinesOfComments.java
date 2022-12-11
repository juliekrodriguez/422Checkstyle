package julie.structuralMetrics.checks;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.DetailAST;


public class LinesOfComments {
  
  public static final String MSG_KEY = "LOC";

  private int LOC = 0;
  
  public int getLOC() {
    return LOC;
  }
  
  public static boolean isAcceptableToken(int type) {
    return Utils.contains(getAcceptableTokens(), type);
  }
  
  public static int[] getAcceptableTokens() {
    return new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN };
  }

  public void visitToken(DetailAST ast) {
    int type = ast.getType();
    if (!isAcceptableToken(type)) {
      return;
    }
    if( TokenTypes.SINGLE_LINE_COMMENT == type) {
      LOC++;
    }
    else if(TokenTypes.BLOCK_COMMENT_BEGIN == type) {
      int comB = ast.getLineNo();
      int comE = ast.getLastChild().getLineNo();
      int NL = comE - comB + 1;
      LOC += NL;
    }
  }
  
  public void beginTree(DetailAST root) {
	LOC = 0;
  }
}
