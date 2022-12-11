package julie.structuralMetrics.checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import julie.structuralMetrics.checks.HTokens;

public class HOperators {
  
  private int count = 0;
  
  public int getHalsteadOperands() {
    return count;
  }
  
  public static boolean isAcceptableToken(int type) {
    return Utils.contains(getAcceptableTokens(), type);
  }

  public static int[] getAcceptableTokens() {
    return HTokens.getOperators();
  }

  public void visitToken(DetailAST ast) {
    if (!isAcceptableToken(ast.getType())) {
      return;
    }
    count++;
  }

  public void beginTree(DetailAST root) {
    count = 0;
  }
}