package julie.structuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

import julie.structuralMetrics.checks.HOperands;

public class HOperandCheck extends AbstractCheck {
  
  private HOperands and = new HOperands();
  
  @Override
  public void beginTree(DetailAST root) {
    and.beginTree(root);
  }

  @Override
  public int[] getDefaultTokens() {
    return getAcceptableTokens();
  }

  @Override
  public int[] getAcceptableTokens() {
    return HOperands.getAcceptableTokens();
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }
    
  @Override
  public void visitToken(DetailAST ast) {
    and.visitToken(ast);
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    int val = and.getHalsteadOperands();
    log(ast.getLineNo(), "Halstead operands: " + val);
  }
}
