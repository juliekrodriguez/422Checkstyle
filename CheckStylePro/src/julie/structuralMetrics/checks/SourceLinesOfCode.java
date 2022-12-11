package julie.structuralMetrics.checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class SourceLinesOfCode {
  
  private boolean useLLOC = false;
  
  private int LOC = 0;
  private int LLOC = 0;
  
  public void setuseLLOC(boolean value) {
    useLLOC = value;
  }
  
  public int getSourceLOC() {
    return useLLOC ? LLOC : LOC;
  }
  
  public static boolean isAcceptableToken(int type) {
    return Utils.contains(getAcceptableTokens(), type);
  }
  
  public static int[] getAcceptableTokens() {
    return new int[] { TokenTypes.SEMI };
  }
  
  public void beginTree(DetailAST rootAST) {
	LLOC = 0;
	LOC = 0;
    DetailAST ast = rootAST;
    DetailAST next = ast.getNextSibling();
    while(null != next) {
      ast = next;
      next = ast.getNextSibling();
    }
    next = ast.getLastChild();
    while (null != next) {
      ast = next;
      next = ast.getLastChild();
    }
    LOC = ast.getLineNo();
  }

  public void visitToken(DetailAST ast) {
    if (!isAcceptableToken(ast.getType())) {
      return;
    }
    LLOC++;
  }

}
