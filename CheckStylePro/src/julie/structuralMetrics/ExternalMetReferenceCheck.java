package julie.structuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;


import com.puppycrawl.tools.checkstyle.api.DetailAST;

import julie.structuralMetrics.checks.ExternalMetReference;

public class ExternalMetReferenceCheck extends AbstractCheck {
  
  ExternalMetReference ref = new ExternalMetReference();
  
  @Override
  public void beginTree(DetailAST root) {
    ref.beginTree(root);
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return ExternalMetReference.getAcceptableTokens();
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
    ref.visitToken(ast);
  }
  
  @Override
  public void leaveToken(DetailAST ast) {
    ref.leaveToken(ast);
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    int val = ref.getExternalMethodReferences();
    log(ast.getLineNo(), "External method ref: " + val);
  }

}
