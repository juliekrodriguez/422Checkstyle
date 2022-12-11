package julie.structuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import julie.structuralMetrics.checks.CyclomaticComplexity;
import julie.structuralMetrics.checks.HVolume;
import julie.structuralMetrics.checks.LinesOfComments;
import julie.structuralMetrics.checks.SourceLinesOfCode;
import julie.structuralMetrics.checks.Utils;

public class MaintainabilityIndexCheck extends AbstractCheck {
  
  public static final String MSG_KEY = "maintainabilityIndex";
  
  private CyclomaticComplexity cc = new CyclomaticComplexity();
  private HVolume hv = new HVolume();
  private SourceLinesOfCode sloc = new SourceLinesOfCode();
  private LinesOfComments locm = new LinesOfComments();
  
  @Override
  public void beginTree(DetailAST root) {
    sloc.setuseLLOC(false);
    
    hv.beginTree(root);
    cc.beginTree(root);
    sloc.beginTree(root);
    locm.beginTree(root);
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return Utils.concatAll(
        HVolume.getAcceptableTokens(), 
        LinesOfComments.getAcceptableTokens(), 
        CyclomaticComplexity.getAcceptableTokens(),
        SourceLinesOfCode.getAcceptableTokens());
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
  public boolean isCommentNodesRequired() {
    return true;
  }

  @Override
  public void visitToken(DetailAST ast) {
    hv.visitToken(ast);
    cc.visitToken(ast);
    sloc.visitToken(ast);
    locm.visitToken(ast);
  }

  private double calcMI() 
  {
    double MI = 171d - 5.2d * Utils.log(hv.getHalsteadVolume(), 2) 
        - 0.23d * (double)cc.getCylomaticComplexity()
        - 16.2d * Utils.log(sloc.getSourceLOC(), 2)
        + 50d * Math.sin(Math.sqrt(2.4d * calcPer()));
    return MI;
  }
  
  @Override
  public void leaveToken(DetailAST ast) 
  {
    cc.leaveToken(ast);
  }
  
  @Override 
  public void finishTree(DetailAST ast) 
  {
	  double val = calcMI();
	  log(ast.getLineNo(), "Maintainability index: " + val);
  }

  private double calcPer() {
    double per = (double)locm.getLOC() / (double)sloc.getSourceLOC();
    return per * (2 * Math.PI);
  }
}
