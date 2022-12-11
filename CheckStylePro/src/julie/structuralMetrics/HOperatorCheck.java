package julie.structuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import julie.structuralMetrics.checks.HTokens;

public class HOperatorCheck extends AbstractCheck {

  private int c = 0;

  @Override
  public int[] getAcceptableTokens() {
    return HTokens.getOperators();
  }

  @Override
  public int[] getDefaultTokens() {
    return getAcceptableTokens();
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public void visitToken(DetailAST ast) {
    c++;
  }
  
  @Override
  public void beginTree(DetailAST root) {
    c = 0;
  }
  
  @Override
  public void finishTree(DetailAST ast) {
	  log(ast.getLineNo(), "Halstead operators: " + c);
  }
}
