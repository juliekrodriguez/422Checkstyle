package julie.structuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

import julie.structuralMetrics.checks.HOperands;
import julie.structuralMetrics.checks.HOperators;
import julie.structuralMetrics.checks.Utils;

public class HLengthCheck extends AbstractCheck {
  
  public static final String MSG_KEY = "halsteadLength";

  private HOperands and = new HOperands();
  private HOperators or = new HOperators();
  
  @Override
  public void beginTree(DetailAST root) {
    and.beginTree(root);
    or.beginTree(root);
  }

  @Override
  public int[] getAcceptableTokens() {
    return Utils.concat(
        HOperands.getAcceptableTokens(), 
        HOperators.getAcceptableTokens());
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
    and.visitToken(ast);
    or.visitToken(ast);
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    int val = and.getHalsteadOperands() + or.getHalsteadOperands();
    log(ast.getLineNo(), "Halstead length: " + val);
  }
}
